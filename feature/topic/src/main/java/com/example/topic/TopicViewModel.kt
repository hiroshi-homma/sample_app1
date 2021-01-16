package com.example.topic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import com.example.core.delegate.TopicAndFollowedDelegate
import com.example.topic.TopicAction.ShowNetWorkCheckDialog
import com.google.gson.Gson
import com.kotlin.project.data.entities.CachedData
import com.kotlin.project.data.model.Hit
import com.kotlin.project.data.model.MyNewsStatus
import com.kotlin.project.data.model.Result
import com.kotlin.project.data.model.Section
import com.kotlin.project.data.model.Sections
import com.kotlin.project.domain.usecase.CachedDataUseCase
import com.kotlin.project.domain.usecase.GetMyNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopicViewModel @Inject constructor(
    application: Application,
    private val getMyNewsUseCase: GetMyNewsUseCase,
    private val cachedDataUseCase: CachedDataUseCase,
    private val topicAndFollowedDelegate: TopicAndFollowedDelegate
) : AndroidViewModel(application),
    LifecycleObserver,
    TopicAndFollowedDelegate by topicAndFollowedDelegate {

    // state
    private val _isDialog = MutableStateFlow(false)
    val isDialog: StateFlow<Boolean> = _isDialog

    // event
    private val _myStatus = MutableSharedFlow<MyNewsStatus>(
        replay = 1, onBufferOverflow = DROP_OLDEST
    )
    val uiState: SharedFlow<MyNewsStatus> = _myStatus

    private val _action = MutableSharedFlow<TopicAction>(
        replay = 1, onBufferOverflow = DROP_OLDEST
    )
    val action: SharedFlow<TopicAction> = _action

    private val _sections = MutableSharedFlow<ArrayList<Section>>(
        replay = 1, onBufferOverflow = DROP_OLDEST
    )
    val sections: SharedFlow<ArrayList<Section>> = _sections

    init {
        fetchData()
    }

    fun onRefresh() {
        fetchData(true)
    }

    fun setIsDialog(isDialog: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _isDialog.emit(isDialog)
        }
    }

    private fun fetchData(isPullToRefresh: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            _myStatus.emit(if (isPullToRefresh) MyNewsStatus.RELOADING else MyNewsStatus.LOADING)
            val data = cachedDataUseCase.getCache()
            when {
                data != null -> {
                    val json =
                        Gson().fromJson(data.cacheJsonString, Sections::class.java) as Sections
                    checkFollowedComparison(json)
                    _sections.emit(json.sections)
                    _myStatus.emit(MyNewsStatus.SUCCESS)
                }
                else -> {
                    fetchApiData()
                    _myStatus.emit(MyNewsStatus.ERROR)
                }
            }
        }
    }

    private fun fetchApiData(isPullToRefresh: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            _myStatus.emit(if (isPullToRefresh) MyNewsStatus.RELOADING else MyNewsStatus.LOADING)
            when (val r = getMyNewsUseCase.getNews()) {
                is Result.Success -> insertCacheData(r.data)
                is Result.Error -> {
                    _action.emit(ShowNetWorkCheckDialog)
                    _myStatus.emit(MyNewsStatus.ERROR)
                }
            }
        }
    }

    private fun insertCacheData(sections: Sections) {
        val jsonString = Gson().toJson(sections) as String
        viewModelScope.launch(Dispatchers.IO) {
            cachedDataUseCase.insert(CachedData(0, jsonString))
            _myStatus.emit(MyNewsStatus.SUCCESS)
            fetchData()
        }
    }

    fun updateCacheData(sp: Int, gp: Int, hp: Int, hit: Hit) {
        val updateData = sections.replayCache.toMutableList()
        updateData.forEach { s ->
            s[sp].groups[gp].hits[hp] = hit
        }
        val jsonString = Gson().toJson(Sections(updateData[0])) as String
        viewModelScope.launch(Dispatchers.IO) {
            cachedDataUseCase.updateCache(1L, jsonString)
        }
        topicAndFollowedDelegate.setUpdateFollowCount(1)
    }

    private fun checkFollowedComparison(json: Sections) {
        var topicFollowedCount = 0
        json.sections.forEach { s ->
            s.groups.forEach { g ->
                g.hits.forEach { h ->
                    if (h.isFollowed) {
                        topicFollowedCount++
                        topicAndFollowedDelegate.setFollowedCount(topicFollowedCount)
                    }
                }
            }
        }
    }
}

sealed class TopicAction {
    object ShowNetWorkCheckDialog : TopicAction()
}

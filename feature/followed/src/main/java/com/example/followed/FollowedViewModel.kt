package com.example.followed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import com.example.core.delegate.TopicAndFollowedDelegate
import com.google.gson.Gson
import com.kotlin.project.data.model.Hit
import com.kotlin.project.data.model.MyNewsStatus
import com.kotlin.project.data.model.Section
import com.kotlin.project.data.model.Sections
import com.kotlin.project.domain.usecase.CachedDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FollowedViewModel @Inject constructor(
    application: Application,
    private val cachedDataUseCase: CachedDataUseCase,
    private val topicAndFollowedDelegate: TopicAndFollowedDelegate
) : AndroidViewModel(application),
    LifecycleObserver,
    TopicAndFollowedDelegate by topicAndFollowedDelegate {

    // state
    private val _uiState = MutableStateFlow<MyNewsStatus>(MyNewsStatus.LOADING)
    val uiState: StateFlow<MyNewsStatus> = _uiState

    // response
    private val _hits = MutableSharedFlow<ArrayList<Hit>>(
        replay = 1,
        onBufferOverflow = DROP_OLDEST
    )
    val hits: SharedFlow<ArrayList<Hit>> = _hits

    private val _sections = MutableSharedFlow<ArrayList<Section>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val sections: SharedFlow<ArrayList<Section>> = _sections

    init {
        fetchData()
    }

    fun onRefresh() {
        fetchData(true)
    }

    private fun fetchData(isPullToRefresh: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(if (isPullToRefresh) MyNewsStatus.RELOADING else MyNewsStatus.LOADING)
            val data = cachedDataUseCase.getCache()
            when {
                data != null -> {
                    val json =
                        Gson().fromJson(data.cacheJsonString, Sections::class.java) as Sections
                    _sections.emit(json.sections)
                    _hits.emit(createHits(json))
                    _uiState.emit(MyNewsStatus.SUCCESS)
                }
                else -> {
                    _uiState.emit(MyNewsStatus.ERROR)
                }
            }
        }
    }

    fun updateCacheData(hp: Int, hit: Hit) {
        val updateData = sections.replayCache.toMutableList()
        updateData.forEach { s ->
            s[hit.updateSectionNumber].groups[hit.updateGroupNumber].hits[hit.updateHitNumber] = hit
        }
        val jsonString = Gson().toJson(Sections(updateData[0])) as String
        viewModelScope.launch(Dispatchers.IO) {
            cachedDataUseCase.updateCache(1L, jsonString)
        }
    }

    private fun createHits(json: Sections): ArrayList<Hit> {
        val hits = arrayListOf<Hit>()
        json.sections.forEach { s ->
            s.groups.forEach { g ->
                g.hits.forEach { h ->
                    if (h.isFollowed) hits.add(h)
                }
            }
        }
        return hits
    }
}

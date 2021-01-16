package com.example.followed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.delegate.TopicAndFollowedDelegate
import com.kotlin.project.data.model.MyNewsStatus
import com.kotlin.project.domain.usecase.CachedDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FollowedViewModel @Inject constructor(
    application: Application,
    private val cachedDataUseCase: CachedDataUseCase,
    private val topicAndFollowedDelegate: TopicAndFollowedDelegate
) : AndroidViewModel(application), LifecycleObserver, TopicAndFollowedDelegate by topicAndFollowedDelegate {

    private val _uiState = MutableStateFlow<MyNewsStatus>(MyNewsStatus.LOADING)
    val uiState: StateFlow<MyNewsStatus> = _uiState

    private val _ids = MutableLiveData<List<String?>>()
    val ids: LiveData<List<String?>> = _ids

    init {
//        fetchData()
    }

    fun onRefresh() {
//        fetchData(true)
    }

    private fun fetchData(isPullToRefresh: Boolean = false) {
        _uiState.value = if (isPullToRefresh) MyNewsStatus.RELOADING else MyNewsStatus.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            val data = cachedDataUseCase.getCache()
            when {
                data != null -> {
                    _uiState.emit(MyNewsStatus.SUCCESS)
                }
                else -> {
                    _uiState.emit(MyNewsStatus.ERROR)
                }
            }
        }
    }
}

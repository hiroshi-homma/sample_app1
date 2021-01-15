package com.example.followed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.delegate.TopicAndFollowedDelegate
import com.kotlin.project.data.entities.FollowData
import com.kotlin.project.data.model.MyNewsStatus
import com.kotlin.project.data.model.Result
import com.kotlin.project.domain.usecase.FollowDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class FollowedViewModel @Inject constructor(
    application: Application,
    private val followDataUseCase: FollowDataUseCase,
    private val topicAndFollowedDelegate: TopicAndFollowedDelegate
) : AndroidViewModel(application), LifecycleObserver, TopicAndFollowedDelegate by topicAndFollowedDelegate {

    private val _uiState = MutableStateFlow<MyNewsStatus>(MyNewsStatus.LOADING)
    val uiState: StateFlow<MyNewsStatus> = _uiState

    private val _followDataList = MediatorLiveData<List<FollowData>>()
    val followDataList: LiveData<List<FollowData>> = _followDataList

    private val _ids = MutableLiveData<List<String?>>()
    val ids: LiveData<List<String?>> = _ids

    init {
        fetchData()
        checkData()
    }

    fun onRefresh() {
        fetchData(true)
        checkData()
        topicAndFollowedDelegate.setIsUpdateFollowed(false)
    }

    fun insertFollowData(followData: FollowData) {
        viewModelScope.launch(Dispatchers.IO) {
            followDataUseCase.insert(followData)
        }
        topicAndFollowedDelegate.setIsUpdateTopic(true)
    }

    fun deleteFollowData(followData: FollowData) {
        viewModelScope.launch(Dispatchers.IO) {
            followDataUseCase.delete(followData)
        }
    }

    fun deleteFollowDataForId(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            followDataUseCase.deleteForId(id)
        }
        topicAndFollowedDelegate.setIsUpdateTopic(true)
    }

    private fun fetchData(isPullToRefresh: Boolean = false) {
        _uiState.value = if (isPullToRefresh) MyNewsStatus.RELOADING else MyNewsStatus.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            when (val r = followDataUseCase.getAll()) {
                is Result.Success -> {
                    _followDataList.postValue(r.data)
                    if (r.data.isNotEmpty()) {
                        _uiState.emit(MyNewsStatus.SUCCESS)
                    } else {
                        _uiState.emit(MyNewsStatus.ERROR)
                    }
                }
                is Result.Error -> {
                    _uiState.emit(MyNewsStatus.ERROR)
                }
            }
        }
    }

    fun checkData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val r = followDataUseCase.getAll()) {
                is Result.Success -> {
                    _ids.postValue(r.data.map { it.id })
                }
                is Result.Error -> {
                    Timber.d("check_error:${r.myNewsError}")
                }
            }
        }
    }
}

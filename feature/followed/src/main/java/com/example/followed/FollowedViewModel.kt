package com.example.followed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.project.data.entities.FollowData
import com.kotlin.project.data.model.MyNewsStatus
import com.kotlin.project.data.model.MyNewsStatus.LOADING
import com.kotlin.project.data.model.Result
import com.kotlin.project.domain.usecase.FollowDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FollowedViewModel @Inject constructor(
    application: Application,
    private val followDataUseCase: FollowDataUseCase
) : AndroidViewModel(application), LifecycleObserver {

    private val _uiState = MutableStateFlow<MyNewsStatus>(LOADING)
    val uiState: StateFlow<MyNewsStatus> = _uiState

    private val _followDataList = MediatorLiveData<List<FollowData>>()
    val followDataList: LiveData<List<FollowData>> = _followDataList

    init {
        fetchData()
    }

    fun insertFollowData(followData: FollowData) {
        viewModelScope.launch(Dispatchers.IO) {
            followDataUseCase.insert(followData)
        }
    }

    fun deleteFollowData(followData: FollowData) {
        viewModelScope.launch(Dispatchers.IO) {
            followDataUseCase.delete(followData)
        }
    }

    fun deleteFollowDataForId(id: String) {
        viewModelScope.launch(Dispatchers.Default) {
            followDataUseCase.deleteForId(id)
        }
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val r = followDataUseCase.getAll()) {
                is Result.Success -> {
                    _followDataList.postValue(r.data)
                    _uiState.emit(MyNewsStatus.SUCCESS)
                }
                is Result.Error -> {
                    _uiState.emit(MyNewsStatus.ERROR)
                }
            }
        }
    }
}

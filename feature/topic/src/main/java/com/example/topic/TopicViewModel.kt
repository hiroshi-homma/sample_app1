package com.example.topic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.project.data.entities.FollowData
import com.kotlin.project.data.model.MyNewsStatus
import com.kotlin.project.data.model.Result
import com.kotlin.project.data.model.Section
import com.kotlin.project.domain.usecase.FollowDataUseCase
import com.kotlin.project.domain.usecase.GetMyNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopicViewModel @Inject constructor(
    application: Application,
    private val getMyNewsUseCase: GetMyNewsUseCase,
    private val followDataUseCase: FollowDataUseCase
) : AndroidViewModel(application), LifecycleObserver {

    private val _uiState = MutableStateFlow<MyNewsStatus>(MyNewsStatus.LOADING)
    val uiState: StateFlow<MyNewsStatus> = _uiState

    private val _sections = MediatorLiveData<ArrayList<Section>>()
    val sections: LiveData<ArrayList<Section>> = _sections

    private val _followData = MediatorLiveData<List<FollowData>>()
    val followData: LiveData<List<FollowData>> = _followData

    private val _ids = MutableLiveData<List<String?>>()
    val ids: LiveData<List<String?>> = _ids

    init {
        fetchData()
        fetchFollowData()
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
        viewModelScope.launch(Dispatchers.IO) {
            followDataUseCase.deleteForId(id)
        }
    }

    fun fetchFollowData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val r = followDataUseCase.getAll()) {
                is Result.Success -> {
                    _ids.postValue(r.data.map { it.id })
                    _followData.postValue(r.data)
                    _uiState.emit(MyNewsStatus.SUCCESS)
                }
                is Result.Error -> {
                    _uiState.emit(MyNewsStatus.ERROR)
                }
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val r = getMyNewsUseCase.getNews()) {
                is Result.Success -> {
                    if (_sections.value == null) _sections.postValue(r.data.sections)
                    _uiState.emit(MyNewsStatus.SUCCESS)
                }
                is Result.Error -> {
                    _uiState.emit(MyNewsStatus.ERROR)
                }
            }
        }
    }
}

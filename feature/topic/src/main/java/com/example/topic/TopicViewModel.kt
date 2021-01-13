package com.example.topic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.project.data.model.MyNewsStatus
import com.kotlin.project.data.model.Result
import com.kotlin.project.data.model.Section
import com.kotlin.project.domain.usecase.GetMyNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopicViewModel @Inject constructor(
    application: Application,
    private val getMyNewsUseCase: GetMyNewsUseCase
) : AndroidViewModel(application), LifecycleObserver {

    private val _uiState = MutableStateFlow<MyNewsStatus>(MyNewsStatus.LOADING)
    val uiState: StateFlow<MyNewsStatus> = _uiState

    private val _sections = MediatorLiveData<ArrayList<Section>>()
    val sections: LiveData<ArrayList<Section>> = _sections

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.Default) {
            when (val r = getMyNewsUseCase.getNews()) {
                is Result.Success -> {
                    _sections.postValue(r.data.sections)
                    _uiState.emit(MyNewsStatus.SUCCESS)
                }
                is Result.Error -> {
                    _uiState.emit(MyNewsStatus.ERROR)
                }
            }
        }
    }
}

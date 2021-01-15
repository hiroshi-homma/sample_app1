package com.example.core.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface TopicAndFollowedDelegate {
    val isUpdateTopic: LiveData<Boolean>
    val isUpdateFollowed: LiveData<Boolean>
    fun setIsUpdateTopic(isUpdate: Boolean)
    fun setIsUpdateFollowed(isUpdate: Boolean)
}

class TopicAndFollowedDelegateImpl : TopicAndFollowedDelegate {
    private val _isUpdateTopic = MutableLiveData<Boolean>()
    override val isUpdateTopic: LiveData<Boolean> = _isUpdateTopic

    private val _isUpdateFollowed = MutableLiveData<Boolean>()
    override val isUpdateFollowed: LiveData<Boolean> = _isUpdateFollowed

    override fun setIsUpdateTopic(isUpdate: Boolean) {
        _isUpdateTopic.postValue(isUpdate)
    }

    override fun setIsUpdateFollowed(isUpdate: Boolean) {
        _isUpdateFollowed.postValue(isUpdate)
    }
}

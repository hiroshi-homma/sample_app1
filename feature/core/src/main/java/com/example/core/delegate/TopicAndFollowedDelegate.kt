package com.example.core.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface TopicAndFollowedDelegate {
    val followedCount: LiveData<Int>
    val topicCount: LiveData<Int>
    val isUpdateFollowed: LiveData<Boolean>
    val isUpdateTopic: LiveData<Boolean>
    fun setFollowedCount(followedCount: Int)
    fun setTopicCount(topicCount: Int)
    fun setIsUpdateFollowed(updateCount: Boolean)
    fun setIsUpdateTopic(updateCount: Boolean)
    fun isComparisonCount(): Boolean
}

class TopicAndFollowedDelegateImpl : TopicAndFollowedDelegate {
    private val _followedCount = MutableLiveData<Int>()
    override val followedCount: LiveData<Int> = _followedCount

    private val _topicCount = MutableLiveData<Int>()
    override val topicCount: LiveData<Int> = _topicCount

    private val _updateFollowCount = MutableLiveData<Boolean>()
    override val isUpdateFollowed: LiveData<Boolean> = _updateFollowCount

    private val _updateTopicCount = MutableLiveData<Boolean>()
    override val isUpdateTopic: LiveData<Boolean> = _updateTopicCount

    override fun setFollowedCount(followedCount: Int) = _followedCount.postValue(followedCount)

    override fun setTopicCount(topicCount: Int) = _topicCount.postValue(topicCount)

    override fun setIsUpdateFollowed(updateCount: Boolean) = _updateFollowCount.postValue(updateCount)

    override fun setIsUpdateTopic(updateCount: Boolean) = _updateTopicCount.postValue(updateCount)

    override fun isComparisonCount(): Boolean {
        val followedCount = followedCount.value ?: 0
        val topicCount = topicCount.value ?: 0
        return followedCount != topicCount
    }
}

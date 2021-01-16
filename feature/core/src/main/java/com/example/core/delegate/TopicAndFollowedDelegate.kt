package com.example.core.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface TopicAndFollowedDelegate {
    val followedCount: LiveData<Int>
    val topicCount: LiveData<Int>
    val updateFollowCount: LiveData<Int>
    val updateTopicCount: LiveData<Int>
    fun setFollowedCount(followedCount: Int)
    fun setTopicCount(topicCount: Int)
    fun setUpdateFollowCount(updateCount: Int)
    fun setUpdateTopicCount(updateCount: Int)
    fun isComparisonCount(): Boolean
}

class TopicAndFollowedDelegateImpl : TopicAndFollowedDelegate {
    private val _followedCount = MutableLiveData<Int>()
    override val followedCount: LiveData<Int> = _followedCount

    private val _topicCount = MutableLiveData<Int>()
    override val topicCount: LiveData<Int> = _topicCount

    private val _updateFollowCount = MutableLiveData<Int>()
    override val updateFollowCount: LiveData<Int> = _updateFollowCount

    private val _updateTopicCount = MutableLiveData<Int>()
    override val updateTopicCount: LiveData<Int> = _updateTopicCount

    override fun setFollowedCount(followedCount: Int) = _followedCount.postValue(followedCount)

    override fun setTopicCount(topicCount: Int) = _topicCount.postValue(topicCount)

    override fun setUpdateFollowCount(updateCount: Int) = _updateFollowCount.postValue(updateCount)

    override fun setUpdateTopicCount(updateCount: Int) = _updateTopicCount.postValue(updateCount)

    override fun isComparisonCount(): Boolean {
        val followedCount = followedCount.value ?: 0
        val topicCount = topicCount.value ?: 0
        return followedCount != topicCount
    }
}

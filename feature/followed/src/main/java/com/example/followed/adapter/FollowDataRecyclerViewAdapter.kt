package com.example.followed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.followed.FollowedViewModel
import com.example.followed.R
import com.example.followed.adapter.FollowDataRecyclerViewAdapter.FollowDataHolder
import com.example.followed.databinding.ItemFollowDataViewBinding
import com.kotlin.project.data.entities.FollowData
import com.kotlin.project.data.entities.transform
import javax.inject.Inject

class FollowDataRecyclerViewAdapter @Inject constructor(
    private val followData: List<FollowData>,
    private val followedViewModel: FollowedViewModel
) : RecyclerView.Adapter<FollowDataHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowDataHolder {
        val binding = DataBindingUtil.inflate<ItemFollowDataViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_follow_data_view,
            parent,
            false
        )
        return FollowDataHolder(
            binding
        )
    }

    override fun getItemCount() = followData.size

    override fun onBindViewHolder(holder: FollowDataHolder, position: Int) {
        holder.binding.hit = followData[position].transform()
        val ids = followedViewModel.ids.value ?: listOf()
        val isFollowed = ids.contains(followData[position].id)
        holder.binding.checkFollow.apply {
            isSelected = if (isFollowed) {
                setImageResource(R.drawable.ic_baseline_check_circle_24)
                true
            } else {
                setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
                false
            }
            setOnClickListener {
                isSelected = !isSelected
                when {
                    isSelected -> {
                        setImageResource(R.drawable.ic_baseline_check_circle_24)
                        followedViewModel.insertFollowData(followData[position])
                    }
                    else -> {
                        setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
                        followData[position].id?.let {
                            followedViewModel.deleteFollowDataForId(it)
                        }
                    }
                }
                followedViewModel.checkData()
            }
        }
    }

    class FollowDataHolder(val binding: ItemFollowDataViewBinding) : RecyclerView.ViewHolder(binding.root)
}

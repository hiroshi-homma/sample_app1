package com.example.followed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.followed.FollowedViewModel
import com.example.followed.R
import com.example.followed.adapter.FollowDataRecyclerViewAdapter.FollowDataHolder
import com.example.followed.databinding.ItemFollowDataViewBinding
import com.kotlin.project.data.model.Hit

class FollowDataRecyclerViewAdapter(
    private val hits: ArrayList<Hit>,
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

    override fun getItemCount() = hits.size

    override fun onBindViewHolder(holder: FollowDataHolder, position: Int) {
        holder.binding.hit = hits[position]
        holder.binding.checkFollow.apply {
            isSelected = if (hits[position].isFollowed) {
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
                        followedViewModel.updateCacheData(
                            hits[position].copy(isFollowed = isSelected)
                        )
                    }
                    else -> {
                        setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
                        followedViewModel.updateCacheData(
                            hits[position].copy(isFollowed = isSelected)
                        )
                    }
                }
            }
        }
    }

    class FollowDataHolder(val binding: ItemFollowDataViewBinding) : RecyclerView.ViewHolder(binding.root)
}

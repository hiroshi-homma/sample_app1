package com.example.followed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.followed.FollowedViewModel
import com.example.followed.R
import com.example.followed.adapter.FollowDataRecyclerViewAdapter.FollowDataHolder
import com.example.followed.databinding.ItemFollowDataViewBinding

class FollowDataRecyclerViewAdapter(
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

    override fun getItemCount() = 0

    override fun onBindViewHolder(holder: FollowDataHolder, position: Int) {
        holder.binding.checkFollow.apply {
            setOnClickListener {
                isSelected = !isSelected
                when {
                    isSelected -> {
                        setImageResource(R.drawable.ic_baseline_check_circle_24)
                    }
                    else -> {
                        setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
                    }
                }
            }
        }
    }

    class FollowDataHolder(val binding: ItemFollowDataViewBinding) : RecyclerView.ViewHolder(binding.root)
}

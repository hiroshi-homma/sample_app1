package com.example.topic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.topic.R
import com.example.topic.TopicViewModel
import com.example.topic.adapter.HitRecyclerViewAdapter.HitHolder
import com.example.topic.databinding.ItemHitViewBinding
import com.kotlin.project.data.model.Hit

class HitRecyclerViewAdapter(
    private val hits: ArrayList<Hit>,
    private val topicViewModel: TopicViewModel,
    private val sectionPosition: Int,
    private val groupPosition: Int
) : RecyclerView.Adapter<HitHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitHolder {
        val binding = DataBindingUtil.inflate<ItemHitViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_hit_view,
            parent,
            false
        )
        return HitHolder(binding)
    }

    override fun getItemCount() = hits.size

    override fun onBindViewHolder(holder: HitHolder, position: Int) {
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
                        topicViewModel.updateCacheData(
                            sectionPosition,
                            groupPosition,
                            position,
                            isSelected
                        )
                    }
                    else -> {
                        setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
                        topicViewModel.updateCacheData(
                            sectionPosition,
                            groupPosition,
                            position,
                            isSelected
                        )
                    }
                }
            }
        }
    }

    class HitHolder(val binding: ItemHitViewBinding) : RecyclerView.ViewHolder(binding.root)
}

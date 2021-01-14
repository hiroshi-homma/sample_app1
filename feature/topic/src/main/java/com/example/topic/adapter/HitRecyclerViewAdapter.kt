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
import com.kotlin.project.data.model.transformForInsert
import timber.log.Timber
import javax.inject.Inject

class HitRecyclerViewAdapter @Inject constructor(
    private val hits: ArrayList<Hit>,
    private val topicViewModel: TopicViewModel,
    private val sectionTitle: String,
    private val groupTitle: String? = ""
) : RecyclerView.Adapter<HitHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitHolder {
        val binding = DataBindingUtil.inflate<ItemHitViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_hit_view,
            parent,
            false
        )
        return HitHolder(
            binding
        )
    }

    override fun getItemCount() = hits.size

    override fun onBindViewHolder(holder: HitHolder, position: Int) {
        holder.binding.hit = hits[position]
        val ids = topicViewModel.ids.value ?: listOf()
        val isFollowed = ids.contains(hits[position].id)
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
                        val e = hits[position].transformForInsert(sectionTitle, groupTitle)
                        topicViewModel.insertFollowData(e)
                        Timber.d("check_data_insert:$e")
                    }
                    else -> {
                        setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
                        hits[position].id?.let {
                            topicViewModel.deleteFollowDataForId(it)
                            Timber.d("check_data_delete:$it")
                        }
                    }
                }
                topicViewModel.fetchFollowData()
            }
        }
    }

    class HitHolder(val binding: ItemHitViewBinding) : RecyclerView.ViewHolder(binding.root)
}

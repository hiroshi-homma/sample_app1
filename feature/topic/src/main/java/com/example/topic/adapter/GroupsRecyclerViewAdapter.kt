package com.example.topic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.topic.R
import com.example.topic.TopicViewModel
import com.example.topic.adapter.GroupsRecyclerViewAdapter.GroupHolder
import com.example.topic.databinding.ItemGroupsViewBinding
import com.kotlin.project.data.model.Groups

class GroupsRecyclerViewAdapter(
    private val groups: ArrayList<Groups>,
    private val topicViewModel: TopicViewModel,
    private val sectionTitle: String
) : RecyclerView.Adapter<GroupHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupHolder {
        val binding = DataBindingUtil.inflate<ItemGroupsViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_groups_view,
            parent,
            false
        )
        return GroupHolder(
            binding
        )
    }

    override fun getItemCount() = groups.size

    override fun onBindViewHolder(holder: GroupHolder, position: Int) {
        holder.binding.groups = groups[position]
        holder.binding.hitRecyclerView.adapter =
            HitRecyclerViewAdapter(
                groups[position].hits,
                topicViewModel,
                sectionTitle,
                groups[position].title
            )
    }

    class GroupHolder(val binding: ItemGroupsViewBinding) : RecyclerView.ViewHolder(binding.root)
}

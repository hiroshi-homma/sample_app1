package com.example.news.ui.topic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.ItemGroupsViewBinding
import com.example.news.ui.topic.adapter.GroupsRecyclerViewAdapter.GroupHolder
import com.kotlin.project.data.model.Groups

class GroupsRecyclerViewAdapter(
    private val groups: ArrayList<Groups>
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

    override fun getItemCount(): Int {
        return groups.size
    }

    override fun onBindViewHolder(holder: GroupHolder, position: Int) {
        holder.binding.groups = groups[position]
        holder.binding.hitRecyclerView.adapter =
            HitRecyclerViewAdapter(groups[position].hits)
    }

    class GroupHolder(val binding: ItemGroupsViewBinding) : RecyclerView.ViewHolder(binding.root)
}
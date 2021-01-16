package com.example.topic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.topic.R
import com.example.topic.TopicViewModel
import com.example.topic.adapter.SectionRecyclerViewAdapter.SectionHolder
import com.example.topic.databinding.ItemSectionsViewBinding
import com.kotlin.project.data.model.Section

class SectionRecyclerViewAdapter(
    private val sections: ArrayList<Section>,
    private val topicViewModel: TopicViewModel
) : RecyclerView.Adapter<SectionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionHolder {
        val binding = DataBindingUtil.inflate<ItemSectionsViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_sections_view,
            parent,
            false
        )
        return SectionHolder(binding)
    }

    override fun getItemCount() = sections.size

    override fun onBindViewHolder(holder: SectionHolder, position: Int) {
        holder.binding.section = sections[position]
        holder.binding.groupRecyclerView.adapter =
            GroupsRecyclerViewAdapter(sections[position].groups, topicViewModel, position)
    }

    class SectionHolder(val binding: ItemSectionsViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}

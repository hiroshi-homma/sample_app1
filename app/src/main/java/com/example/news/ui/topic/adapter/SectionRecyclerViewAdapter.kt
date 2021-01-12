package com.example.news.ui.topic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.ItemSectionsViewBinding
import com.example.news.ui.topic.adapter.SectionRecyclerViewAdapter.SectionHolder
import com.kotlin.project.data.model.Section

class SectionRecyclerViewAdapter(
    private val sections: ArrayList<Section>
) : RecyclerView.Adapter<SectionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionHolder {
        val binding = DataBindingUtil.inflate<ItemSectionsViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_sections_view,
            parent,
            false
        )
        return SectionHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    override fun onBindViewHolder(holder: SectionHolder, position: Int) {
        holder.binding.section = sections[position]
        holder.binding.groupRecyclerView.adapter =
            GroupsRecyclerViewAdapter(sections[position].groups)
    }

    class SectionHolder(val binding: ItemSectionsViewBinding) : RecyclerView.ViewHolder(binding.root)
}
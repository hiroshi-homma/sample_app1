package com.example.topic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.core.databinding.FragmentTopicBinding
import com.example.topic.adapter.SectionRecyclerViewAdapter
import javax.inject.Inject

class TopicFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val topicViewModel: TopicViewModel by viewModels { factory }

    private lateinit var binding: FragmentTopicBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopicBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        lifecycle.addObserver(topicViewModel)
        observe()
        return binding.root
    }

    private fun observe() {
        topicViewModel.sections.observe(viewLifecycleOwner) { sections ->
            binding.sectionRecyclerView.adapter = SectionRecyclerViewAdapter(sections)
        }
    }
}

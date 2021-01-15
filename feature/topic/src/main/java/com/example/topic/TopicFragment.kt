package com.example.topic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.topic.adapter.SectionRecyclerViewAdapter
import com.example.topic.databinding.FragmentTopicBinding
import com.kotlin.project.data.model.MyNewsStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            this.swipeRefresh.setOnRefreshListener {
                topicViewModel.onRefresh()
            }
        }
    }

    private fun observe() {
        topicViewModel.sections.observe(viewLifecycleOwner) { sections ->
            binding.apply {
                progressBar.visibility = View.GONE
                sectionRecyclerView.apply {
                    visibility = View.VISIBLE
                    adapter = SectionRecyclerViewAdapter(sections, topicViewModel)
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            topicViewModel.uiState.collect { state ->
                when (state) {
                    is MyNewsStatus.RELOADING -> binding.swipeRefresh.isRefreshing = true
                    else -> binding.swipeRefresh.isRefreshing = false
                }
            }
        }

        topicViewModel.isUpdateTopic.observe(viewLifecycleOwner) {
            if (it) topicViewModel.onRefresh()
        }
    }
}

package com.example.topic

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.topic.TopicAction.ShowNetWorkCheckDialog
import com.example.topic.adapter.SectionRecyclerViewAdapter
import com.example.topic.databinding.FragmentTopicBinding
import com.kotlin.project.data.model.MyNewsStatus
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
                progressBar.visibility = View.VISIBLE
                sectionRecyclerView.visibility = View.GONE
                topicViewModel.onRefresh()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        topicViewModel.isDialog.value.let {
            if (it) topicViewModel.onRefresh()
        }
    }

    private fun observe() {
        topicViewModel.isUpdateTopic.observe(viewLifecycleOwner) {
            topicViewModel.onRefresh()
        }

        lifecycleScope.launch {
            topicViewModel.action.collect { action ->
                when (action) {
                    is ShowNetWorkCheckDialog -> {
                        netWorkCheckDialog()
                        topicViewModel.setIsDialog(true)
                    }
                }
            }
        }
        lifecycleScope.launch {
            topicViewModel.sections.collect { sections ->
                binding.apply {
                    progressBar.visibility = View.GONE
                    sectionRecyclerView.apply {
                        visibility = View.VISIBLE
                        adapter = SectionRecyclerViewAdapter(sections, topicViewModel)
                    }
                }
            }
        }
        lifecycleScope.launch {
            topicViewModel.uiState.collect { state ->
                when (state) {
                    is MyNewsStatus.RELOADING -> binding.swipeRefresh.isRefreshing = true
                    else -> binding.swipeRefresh.isRefreshing = false
                }
            }
        }
    }

    private fun netWorkCheckDialog() {
        context?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.network_check)
                .setCancelable(false)
                .setPositiveButton(R.string.move_setting) { _, _ ->
                    val intent = Intent()
                    intent.action = ACTION_AIRPLANE_MODE_SETTINGS
                    startActivity(intent)
                }
            builder.create().show()
        }
    }
}

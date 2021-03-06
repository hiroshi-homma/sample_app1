package com.example.followed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.followed.adapter.FollowDataRecyclerViewAdapter
import com.example.followed.databinding.FragmentFollowedBinding
import com.kotlin.project.data.model.MyNewsStatus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FollowedFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val followedViewModel: FollowedViewModel by viewModels { factory }

    private lateinit var binding: FragmentFollowedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowedBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        lifecycle.addObserver(followedViewModel)
        observe()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            this.swipeRefresh.setOnRefreshListener {
                progressBar.visibility = View.VISIBLE
                sectionRecyclerView.visibility = View.GONE
                followedViewModel.onRefresh()
            }
        }
    }

    private fun observe() {
        followedViewModel.isUpdateFollowed.observe(viewLifecycleOwner) {
            followedViewModel.onRefresh()
        }

        lifecycleScope.launch {
            followedViewModel.hits.collect { hits ->
                binding.apply {
                    progressBar.visibility = View.GONE
                    sectionRecyclerView.apply {
                        visibility = View.VISIBLE
                        adapter = FollowDataRecyclerViewAdapter(hits, followedViewModel)
                    }
                }
            }
        }
        lifecycleScope.launch {
            followedViewModel.myStatus.collect { state ->
                when (state) {
                    is MyNewsStatus.RELOADING -> binding.swipeRefresh.isRefreshing = true
                    else -> binding.swipeRefresh.isRefreshing = false
                }
            }
        }
    }
}

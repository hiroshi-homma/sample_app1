package com.example.followed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.followed.adapter.FollowDataRecyclerViewAdapter
import com.example.followed.databinding.FragmentFollowedBinding
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

    private fun observe() {
        followedViewModel.followDataList.observe(viewLifecycleOwner) { followData ->
            binding.apply {
                progressBar.visibility = View.GONE
                sectionRecyclerView.apply {
                    visibility = View.VISIBLE
                    adapter = FollowDataRecyclerViewAdapter(followData, followedViewModel)
                }
            }
        }
    }
}

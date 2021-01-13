package com.example.followed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.followed.databinding.FragmentFollwedBinding
import javax.inject.Inject

class FollowedFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val followedViewModel: FollowedViewModel by viewModels { factory }

    private lateinit var binding : FragmentFollwedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollwedBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        lifecycle.addObserver(followedViewModel)
        return binding.root
    }
}

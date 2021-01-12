package com.example.news.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.news.R.id
import com.example.news.R.layout
import com.example.news.databinding.ActivityMyNewsBinding
import dagger.android.support.DaggerAppCompatActivity

class MyNewsActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMyNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout.activity_my_news)
        val navHostFragment = supportFragmentManager.findFragmentById(id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)
    }
}

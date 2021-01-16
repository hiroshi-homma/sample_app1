package com.example.core.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.core.R
import com.example.core.databinding.ActivityMyNewsBinding
import com.example.core.delegate.TopicAndFollowedDelegate
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MyNewsActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMyNewsBinding
    private var backCount = 0

    @Inject
    lateinit var topicAndFollowedDelegate: TopicAndFollowedDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_news)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)
        setBottomNavigationListener(navController)
    }

    override fun onBackPressed() {
        backCount++
        val toast: Toast = Toast.makeText(this, R.string.close_app, Toast.LENGTH_SHORT)
        if (backCount == 2) toast.cancel()
        when {
            binding.navView.selectedItemId != R.id.navigation_topic -> {
                binding.navView.selectedItemId = R.id.navigation_topic
                backCount = 0
            }
            else -> {
                // Backボタンを連続で押したときにアプリを終了する
                when (backCount) {
                    2 -> finish()
                    else -> {
                        toast.show()
                        Handler(Looper.getMainLooper()).postDelayed(
                            {
                                backCount = 0
                            },
                            1500
                        )
                    }
                }
            }
        }
    }

    private fun setBottomNavigationListener(navController: NavController) {
        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_topic -> {
                    if (topicAndFollowedDelegate.isComparisonCount()) {
                        topicAndFollowedDelegate.setIsUpdateFollowed(true)
                    }
                    navController.navigate(R.id.navigation_topic)
                }
                R.id.navigation_followed -> {
                    navController.navigate(R.id.navigation_followed)
                }
            }
            true
        }
    }
}

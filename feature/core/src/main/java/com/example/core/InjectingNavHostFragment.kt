package com.example.core

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.example.core.navigation.ShowHideNavHostFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class InjectingNavHostFragment : ShowHideNavHostFragment() {

    @Inject
    lateinit var daggerFragmentInjectionFactory: FragmentFactory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = daggerFragmentInjectionFactory
        super.onCreate(savedInstanceState)
    }
}

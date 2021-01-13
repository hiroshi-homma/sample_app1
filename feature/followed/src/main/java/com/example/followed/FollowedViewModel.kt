package com.example.followed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import javax.inject.Inject

class FollowedViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application), LifecycleObserver {

}

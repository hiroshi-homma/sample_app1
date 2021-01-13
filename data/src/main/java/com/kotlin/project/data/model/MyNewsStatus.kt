package com.kotlin.project.data.model

sealed class MyNewsStatus {
    object SUCCESS : MyNewsStatus()
    object ERROR : MyNewsStatus()
    object LOADING : MyNewsStatus()
    object RELOADING : MyNewsStatus()
}

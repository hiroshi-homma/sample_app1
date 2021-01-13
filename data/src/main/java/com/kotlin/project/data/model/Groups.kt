package com.kotlin.project.data.model

data class Groups(
    val title: String,
    val hits: ArrayList<Hit> = arrayListOf()
)

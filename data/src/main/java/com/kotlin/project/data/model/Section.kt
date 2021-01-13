package com.kotlin.project.data.model

data class Section(
    val title: String,
    val groups: ArrayList<Groups> = arrayListOf()
)

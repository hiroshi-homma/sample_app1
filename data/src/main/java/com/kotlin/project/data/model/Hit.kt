package com.kotlin.project.data.model

data class Hit(
    val id: String,
    val label: String,
    val type: String,
    val followersCount: Int
) {
    val followersCountToString: String
        get() = "$followersCount 人にフォロー"
}
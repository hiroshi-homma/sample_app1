package com.kotlin.project.data.model

data class Hit(
    val id: String?,
    val label: String?,
    val type: String?,
    val followersCount: Int?,
    var isFollowed: Boolean = false
) {
    val followersCountToString: String
        get() = "$followersCount 人にフォロー"
}

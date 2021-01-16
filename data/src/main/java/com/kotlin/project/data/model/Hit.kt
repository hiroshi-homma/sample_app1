package com.kotlin.project.data.model

data class Hit(
    val id: String?,
    val label: String?,
    val type: String?,
    val followersCount: Int?,
    var isFollowed: Boolean = false,
    var updateSectionNumber: Int = 0,
    var updateGroupNumber: Int = 0,
    var updateHitNumber: Int = 0
) {
    val followersCountToString: String
        get() = "$followersCount 人にフォロー"
}

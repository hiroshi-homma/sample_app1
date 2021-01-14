package com.kotlin.project.data.model

import com.kotlin.project.data.entities.FollowData

data class Hit(
    val id: String?,
    val label: String?,
    val type: String?,
    val followersCount: Int?
) {
    val followersCountToString: String
        get() = "$followersCount 人にフォロー"
}

fun Hit.transformForInsert(sectionTitle: String, groupTitle: String? = ""): FollowData {
    return FollowData(
        0,
        id = id,
        sectionName = sectionTitle,
        groupName = groupTitle,
        label = label,
        type = type,
        followersCount = followersCount,
    )
}

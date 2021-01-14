package com.kotlin.project.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kotlin.project.data.model.Hit

@Entity
data class FollowData(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "id") val id: String?,
    @ColumnInfo(name = "section_name") val sectionName: String?,
    @ColumnInfo(name = "group_name") val groupName: String?,
    @ColumnInfo(name = "label") val label: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "followers_count") val followersCount: Int?
)

fun FollowData.transform(): Hit {
    return Hit(
        id = id,
        label = label,
        type = type,
        followersCount = followersCount,
    )
}

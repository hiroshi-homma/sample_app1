package com.kotlin.project.useForTesting

import com.kotlin.project.data.model.Groups
import com.kotlin.project.data.model.Hit
import com.kotlin.project.data.model.Section
import com.kotlin.project.data.model.Sections

object TestData {
    const val sectionSize = 3

    fun dummySections(): Sections {

        val array1 = arrayListOf<Section>()
        val array2 = arrayListOf<Groups>()
        val array3 = arrayListOf<Hit>()

        val dummyHitList: List<Hit> = (0..10).map { dummyHit() }
        array3.addAll(dummyHitList)

        val dummyGroupsList: List<Groups> = (0..10).map { dummyGroup(it, array3) }
        array2.addAll(dummyGroupsList)

        val dummySectionList: List<Section> = (0..10).map { dummySection(it, array2) }
        array1.addAll(dummySectionList)
        return Sections(array1)
    }

    private fun dummySection(i: Int, groupsList: ArrayList<Groups>) = Section(
        title = "testSection$i",
        groups = groupsList
    )

    private fun dummyGroup(i: Int, hitList: ArrayList<Hit>) = Groups(
        title = "testGroup$i",
        hits = hitList
    )

    private fun dummyHit() = Hit(
        id = "technology_DF_SECF_C4____",
        label = "TestLabel",
        type = "topic",
        followersCount = 19198
    )
}

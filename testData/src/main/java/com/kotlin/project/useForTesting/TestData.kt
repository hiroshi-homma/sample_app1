package com.kotlin.project.useForTesting

import com.kotlin.project.data.entities.CachedData
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

    // viewModel
    val testCacheData = CachedData(
        uid = 0,
        cacheJsonString = "{\n" +
            "    \"sections\": [\n" +
            "        {\n" +
            "            \"title\": \"TopicA\",\n" +
            "            \"groups\": [\n" +
            "                {\n" +
            "                    \"title\": null,\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_A1\",\n" +
            "                            \"label\": \"Test_Topic1\",\n" +
            "                            \"type\": \"topicA\",\n" +
            "                            \"followers_count\": 1000\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_A2\",\n" +
            "                            \"label\": \"Test_Topic2\",\n" +
            "                            \"type\": \"topicA\",\n" +
            "                            \"followers_count\": 2000\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_A3\",\n" +
            "                            \"label\": \"Test_Topic3\",\n" +
            "                            \"type\": \"topicA\",\n" +
            "                            \"followers_count\": 3000\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_A4\",\n" +
            "                            \"label\": \"Test_Topic4\",\n" +
            "                            \"type\": \"topicA\",\n" +
            "                            \"followers_count\": 4000\n" +
            "                        }\n" +
            "                    ]\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"TopicB\",\n" +
            "            \"groups\": [\n" +
            "                {\n" +
            "                    \"title\": \"TopicB_Title1\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title1_1\",\n" +
            "                            \"label\": \"Test_Topic5\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 4612\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title1_2\",\n" +
            "                            \"label\": \"Test_Topic6\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 5093\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title1_3\",\n" +
            "                            \"label\": \"Test_Topic7\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 1284\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title1_4\",\n" +
            "                            \"label\": \"Test_Topic8\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2574\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"TopicB_Title2\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title2_1\",\n" +
            "                            \"label\": \"Test_Topic9\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2296\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title2_2\",\n" +
            "                            \"label\": \"Test_Topic10\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2526\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"TopicB_Title3\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title3_1\",\n" +
            "                            \"label\": \"Test_Topic11\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 1684\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title3_2\",\n" +
            "                            \"label\": \"Test_Topic12\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 1934\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"TopicB_Title4\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title4_1\",\n" +
            "                            \"label\": \"Test_Topic13\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 3375\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title4_2\",\n" +
            "                            \"label\": \"Test_Topic14\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 3302\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title4_3\",\n" +
            "                            \"label\": \"Test_Topic15\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2944\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title4_4\",\n" +
            "                            \"label\": \"Test_Topic16\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2182\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"TopicB_Title5\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title5_1\",\n" +
            "                            \"label\": \"Test_Topic17\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 12931\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title5_2\",\n" +
            "                            \"label\": \"Test_Topic18\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 3653\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title5_3\",\n" +
            "                            \"label\": \"Test_Topic19\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2700\n" +
            "                        }\n" +
            "\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"TopicB_Title6\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title6_1\",\n" +
            "                            \"label\": \"Test_Topic20\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2795\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title6_2\",\n" +
            "                            \"label\": \"Test_Topic21\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2953\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"TopicB_Title7\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title7_1\",\n" +
            "                            \"label\": \"Test_Topic22\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2526\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title7_2\",\n" +
            "                            \"label\": \"Test_Topic23\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2305\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"TopicB_Title8\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title8_1\",\n" +
            "                            \"label\": \"Test_Topic24\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2268\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title8_2\",\n" +
            "                            \"label\": \"Test_Topic25\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2305\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"TopicB_Title9\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title9_1\",\n" +
            "                            \"label\": \"Test_Topic26\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 9412\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title9_2\",\n" +
            "                            \"label\": \"Test_Topic27\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 6875\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title9_3\",\n" +
            "                            \"label\": \"Test_Topic28\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 4239\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title9_4\",\n" +
            "                            \"label\": \"Test_Topic29\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2441\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title9_5\",\n" +
            "                            \"label\": \"Test_Topic30\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 8878\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title9_6\",\n" +
            "                            \"label\": \"Test_Topic31\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2302\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title9_7\",\n" +
            "                            \"label\": \"Test_Topic32\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2100\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"TopicB_Title10\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title10_1\",\n" +
            "                            \"label\": \"Test_Topic33\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 6608\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title10_2\",\n" +
            "                            \"label\": \"Test_Topic34\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2553\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicB_Title10_3\",\n" +
            "                            \"label\": \"Test_Topic35\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 1111\n" +
            "                        }\n" +
            "                    ]\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"TopicC\",\n" +
            "            \"groups\": [\n" +
            "                {\n" +
            "                    \"title\": null,\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicC_Title10_1\",\n" +
            "                            \"label\": \"Test_Topic36\",\n" +
            "                            \"type\": \"topic\",\n" +
            "                            \"followers_count\": 57767\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicC_Title10_2\",\n" +
            "                            \"label\": \"Test_Topic37\",\n" +
            "                            \"type\": \"topic\",\n" +
            "                            \"followers_count\": 119340\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicC_Title10_3\",\n" +
            "                            \"label\": \"Test_Topic38\",\n" +
            "                            \"type\": \"topic\",\n" +
            "                            \"followers_count\": 36363\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"id_test_TopicC_Title10_4\",\n" +
            "                            \"label\": \"Test_Topic39\",\n" +
            "                            \"type\": \"topic\",\n" +
            "                            \"followers_count\": 36410\n" +
            "                        }\n" +
            "                    ]\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}"
    )
}

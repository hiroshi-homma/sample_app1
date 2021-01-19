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
            "            \"title\": \"人気のトピック\",\n" +
            "            \"groups\": [\n" +
            "                {\n" +
            "                    \"title\": null,\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"topic_DF_TE_17101907\",\n" +
            "                            \"label\": \"自動運転\",\n" +
            "                            \"type\": \"topic\",\n" +
            "                            \"followers_count\": 5337\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"technology_DF_SECF_C4____\",\n" +
            "                            \"label\": \"フィンテック\",\n" +
            "                            \"type\": \"topic\",\n" +
            "                            \"followers_count\": 19198\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"technology_DF_SECF_C1____\",\n" +
            "                            \"label\": \"AI\",\n" +
            "                            \"type\": \"topic\",\n" +
            "                            \"followers_count\": 30884\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"technology_DF_SECF_C2____\",\n" +
            "                            \"label\": \"IoT\",\n" +
            "                            \"type\": \"topic\",\n" +
            "                            \"followers_count\": 5133\n" +
            "                        }\n" +
            "                    ]\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"注目の企業\",\n" +
            "            \"groups\": [\n" +
            "                {\n" +
            "                    \"title\": \"銀行\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"0071006\",\n" +
            "                            \"label\": \"みずほフィナンシャルグループ\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 4612\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0070182\",\n" +
            "                            \"label\": \"三菱ＵＦＪフィナンシャル・グループ\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 5093\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0070190\",\n" +
            "                            \"label\": \"りそなホールディングス\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 1284\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0071004\",\n" +
            "                            \"label\": \"三井住友フィナンシャルグループ\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2574\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"空運\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"0001941\",\n" +
            "                            \"label\": \"日本航空\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2296\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0001942\",\n" +
            "                            \"label\": \"ＡＮＡホールディングス\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2526\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"保険\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"0070505\",\n" +
            "                            \"label\": \"損害保険ジャパン日本興亜\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 1684\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0070501\",\n" +
            "                            \"label\": \"東京海上日動火災保険\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 1934\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"総合商社\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"0001592\",\n" +
            "                            \"label\": \"三菱商事\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 3375\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0001551\",\n" +
            "                            \"label\": \"伊藤忠商事\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 3302\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0001574\",\n" +
            "                            \"label\": \"三井物産\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2944\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0001588\",\n" +
            "                            \"label\": \"住友商事\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2182\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"自動車\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"0001353\",\n" +
            "                            \"label\": \"トヨタ自動車\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 12931\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0001351\",\n" +
            "                            \"label\": \"日産自動車\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 3653\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0007267\",\n" +
            "                            \"label\": \"ホンダ\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2700\n" +
            "                        }\n" +
            "\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"情報機器・通信機器\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"0000638\",\n" +
            "                            \"label\": \"富士フイルムホールディングス\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2795\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0001458\",\n" +
            "                            \"label\": \"キヤノン\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2953\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"総合小売・食料品小売\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"0031087\",\n" +
            "                            \"label\": \"セブン＆アイ・ホールディングス\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2526\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0001683\",\n" +
            "                            \"label\": \"イオン\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2305\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"産業用装置・重電設備\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"0001325\",\n" +
            "                            \"label\": \"三菱重工業\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2268\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0001683\",\n" +
            "                            \"label\": \"イオン\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2305\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"家庭用電気機器\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"0006752\",\n" +
            "                            \"label\": \"パナソニック\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 9412\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0001213\",\n" +
            "                            \"label\": \"ソニー\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 6875\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0001161\",\n" +
            "                            \"label\": \"日立製作所\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 4239\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0001191\",\n" +
            "                            \"label\": \"富士通\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2441\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0001162\",\n" +
            "                            \"label\": \"東芝\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 8878\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0006503\",\n" +
            "                            \"label\": \"三菱電機\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2302\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0006701\",\n" +
            "                            \"label\": \"NEC\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2100\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"通信サービス\",\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"0015926\",\n" +
            "                            \"label\": \"ソフトバンクグループ\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 6608\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0017488\",\n" +
            "                            \"label\": \"NTTドコモ\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 2553\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"0004111\",\n" +
            "                            \"label\": \"KDDI\",\n" +
            "                            \"type\": \"company\",\n" +
            "                            \"followers_count\": 1111\n" +
            "                        }\n" +
            "                    ]\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"人気のコラム\",\n" +
            "            \"groups\": [\n" +
            "                {\n" +
            "                    \"title\": null,\n" +
            "                    \"hits\": [\n" +
            "                        {\n" +
            "                            \"id\": \"topic_DF_TB_17090318\",\n" +
            "                            \"label\": \"ニュースこう読む\",\n" +
            "                            \"type\": \"topic\",\n" +
            "                            \"followers_count\": 57767\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"culture_DF_SECH_C3____\",\n" +
            "                            \"label\": \"私の履歴書\",\n" +
            "                            \"type\": \"topic\",\n" +
            "                            \"followers_count\": 119340\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"topic_DF_TB_17092100\",\n" +
            "                            \"label\": \"やさしい経済学\",\n" +
            "                            \"type\": \"topic\",\n" +
            "                            \"followers_count\": 36363\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"id\": \"wappen_44GN44KH44GG44Gu44GT44Go44Gw\",\n" +
            "                            \"label\": \"きょうのことば\",\n" +
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

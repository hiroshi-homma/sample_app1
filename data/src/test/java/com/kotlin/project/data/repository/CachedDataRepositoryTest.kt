package com.kotlin.project.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kotlin.project.data.dao.CachedDataDao
import com.kotlin.project.useForTesting.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("TestFunctionName")
@ExperimentalCoroutinesApi
class CachedDataRepositoryTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    private val testDispatcher = TestCoroutineDispatcher()

    @RelaxedMockK
    lateinit var cachedDataDao: CachedDataDao

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun execute_Success() = testDispatcher.runBlockingTest {
        // arrange
        coEvery {
            cachedDataDao.getCache()
        } returns TestData.testCacheData
        // act
        val r = CachedDataRepositoryImpl(cachedDataDao)
        // assert
        println("check_data_success:${r.getCache()}")
        assert(r.getCache() == TestData.testCacheData)
    }
}

package com.kotlin.project.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kotlin.project.data.model.Sections
import com.kotlin.project.data.model.data
import com.kotlin.project.data.repository.GetMyNewsRepository
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
class GetMyNewsUseCaseTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    private val testDispatcher = TestCoroutineDispatcher()

    @RelaxedMockK
    lateinit var getMyNewsRepository: GetMyNewsRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun execute_Success() = testDispatcher.runBlockingTest {
        // arrange
        coEvery {
            getMyNewsRepository.getNews()
        } returns TestData.dummySections()
        // act
        val u = GetMyNewsUseCaseImpl(getMyNewsRepository)
        // assert
        println("check_data_success:${u.getNews().data}")
        assert(u.getNews().data == TestData.dummySections())
    }

    @Test
    fun execute_Error_UnknownError() = testDispatcher.runBlockingTest {
        // arrange
        coEvery {
            getMyNewsRepository.getNews()
        } returns Sections()
        // act
        val u = GetMyNewsUseCaseImpl(getMyNewsRepository)
        // assert
        println("check_data_error:${u.getNews().data}")
        assert(u.getNews().data == Sections())
    }
}

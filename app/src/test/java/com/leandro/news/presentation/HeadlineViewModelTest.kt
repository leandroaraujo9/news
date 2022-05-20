package com.leandro.news.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.leandro.news.base.Resource
import com.leandro.news.data.repository.FakeHeadlineRepository
import com.leandro.news.domain.model.Article
import com.leandro.news.presentation.headline.HeadlineViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class HeadlineViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HeadlineViewModel

    private val dispatcher = StandardTestDispatcher()

    @MockK(relaxed = true)
    private lateinit var headlineRepository: FakeHeadlineRepository

    @Before
    fun setUp() {
        headlineRepository = mockk()
        viewModel = HeadlineViewModel(headlineRepository)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getHeadline_withEmptyList() = runTest {
        //Given
        coEvery { headlineRepository.getTopHeadlines() } returns flow {
            emit(Resource.Success(emptyList()))
        }

        //When
        viewModel.getHeadline()
        advanceUntilIdle()

        //Then
        assertThat(viewModel.uiState.value.articles).isEmpty()
    }

    @Test
    fun getHeadline_withArticles() = runTest {
        //Given
        coEvery { headlineRepository.getTopHeadlines() } returns flow {
            emit(Resource.Success(articles))
        }

        //When
        viewModel.getHeadline()
        advanceUntilIdle()

        //Then
        assertThat(viewModel.uiState.value.articles).isEqualTo(articles)
    }

    @Test
    fun getHeadline_withLoadingStateTrue() = runTest {
        //Given
        coEvery { headlineRepository.getTopHeadlines() } returns flow {
            emit(Resource.Loading(true))
        }

        //When
        viewModel.getHeadline()
        advanceUntilIdle()

        //Then
        assertThat(viewModel.uiState.value.isLoading).isTrue()
    }

    @Test
    fun getHeadline_withLoadingStateFalse() = runTest {
        //Given
        coEvery { headlineRepository.getTopHeadlines() } returns flow {
            emit(Resource.Loading(false))
        }

        //When
        viewModel.getHeadline()
        advanceUntilIdle()

        //Then
        assertThat(viewModel.uiState.value.isLoading).isFalse()
    }

    @Test
    fun getHeadline_withErrorState() = runTest {
        //Given
        val errorMessade = "Unexpected error"
        coEvery { headlineRepository.getTopHeadlines() } returns flow {
            emit(Resource.Error(errorMessade))
        }

        //When
        viewModel.getHeadline()
        advanceUntilIdle()

        //Then
        assertThat(viewModel.uiState.value.errorMessage).isEqualTo(errorMessade)
    }

    private val articles = listOf(
        Article(
            author = "Author 1",
            content = "Content 1",
            description = "Description 1",
            published = Date(),
            title = "Title 1",
            url = "https://www.cbsnews.com",
            image = "https://www.cbsnews.com"
        ),
        Article(
            author = "Author 2",
            content = "Content 2",
            description = "Description 2",
            published = Date(),
            title = "Title 2",
            url = "https://www.cbsnews.com",
            image = "https://www.cbsnews.com"
        ),
        Article(
            author = "Author 3",
            content = "Content 3",
            description = "Description 3",
            published = Date(),
            title = "Title 3",
            url = "https://www.cbsnews.com",
            image = "https://www.cbsnews.com"
        ),
    )

}
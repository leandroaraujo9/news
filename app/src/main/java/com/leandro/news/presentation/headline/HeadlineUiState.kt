package com.leandro.news.presentation.headline

import com.leandro.news.domain.model.Article

data class HeadlineUiState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null
)
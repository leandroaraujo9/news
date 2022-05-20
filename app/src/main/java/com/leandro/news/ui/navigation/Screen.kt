package com.leandro.news.ui.navigation

sealed class Screen(val route: String) {
    object Headline : Screen("headline")
    object HeadlineDetail : Screen("headline-detail/article={article}")
}
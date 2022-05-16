package com.leandro.news.ui.navigation

sealed class Screen(val route: String) {
    object News : Screen("news")
    object NewsDetail : Screen("news_detail")
}
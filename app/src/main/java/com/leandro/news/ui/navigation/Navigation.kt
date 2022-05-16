package com.leandro.news.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leandro.news.presentation.news.NewsScreen
import com.leandro.news.presentation.news_detail.NewsDetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.News.route){
        composable(route = Screen.News.route){
            NewsScreen(navController = navController)
        }
        composable(route = Screen.NewsDetail.route){
            NewsDetailScreen()
        }
    }
}
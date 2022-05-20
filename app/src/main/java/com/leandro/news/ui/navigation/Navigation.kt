package com.leandro.news.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leandro.news.domain.model.Article
import com.leandro.news.domain.toObject
import com.leandro.news.presentation.headline.HeadlineScreen
import com.leandro.news.presentation.headline.HeadlineDetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Headline.route){
        composable(route = Screen.Headline.route){
            HeadlineScreen(navController = navController)
        }
        composable(
            route = Screen.HeadlineDetail.route,
            arguments = listOf(
                navArgument("article") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ){ entry ->
            val articleJson = entry.arguments?.getString("article")
            val article = articleJson?.toObject<Article>()
            article?.let { it ->
                HeadlineDetailScreen(article = it, navController = navController)
            }
        }
    }
}
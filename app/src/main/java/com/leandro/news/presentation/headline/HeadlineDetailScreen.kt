package com.leandro.news.presentation.headline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.leandro.news.domain.model.Article

@Composable
fun HeadlineDetailScreen(
    article: Article,
    navController: NavController
) {
    //TODO
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(text = article.author)
    }
}
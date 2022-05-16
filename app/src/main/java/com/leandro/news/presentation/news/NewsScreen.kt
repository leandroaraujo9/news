package com.leandro.news.presentation.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leandro.news.ui.navigation.Screen

@Composable
fun NewsScreen(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(text = "NewsScreen")

        Button(
            modifier = Modifier
                .height(50.dp)
                .width(100.dp),
            onClick = {
                navController.navigate(Screen.NewsDetail.route)
            }
        ){
            Text(text = "Details")
        }
    }
}
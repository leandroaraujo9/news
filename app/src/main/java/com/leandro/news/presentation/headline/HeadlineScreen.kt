package com.leandro.news.presentation.headline

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HeadlineScreen(
    navController: NavController,
    viewModel: HeadlineViewModel = hiltViewModel()
){
    LaunchedEffect(true){
        viewModel.getHeadline()
    }

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.uiState.value.isRefreshing
    )

    val isLoadingState = viewModel.uiState.value.isLoading
    val articleState = viewModel.uiState.value.articles

    if(isLoadingState){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else if(articleState.isEmpty()){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                onClick = {
                    viewModel.getHeadline()
                }
            ) {
                Text(text = "Reload")
            }
        }
    }

    if(articleState.isNotEmpty()){
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(HeadlineEvent.Refresh)
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
            ) {
                items(articleState.size) { i ->
                    val article = articleState[i]
                    HeadlineItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(200.dp)
                            .padding(top = 10.dp, bottom = 10.dp),
                        article = article
                    )
                }
            }
        }
    }
}
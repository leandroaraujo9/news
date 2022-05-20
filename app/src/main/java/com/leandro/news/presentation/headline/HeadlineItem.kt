package com.leandro.news.presentation.headline

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.leandro.news.domain.model.Article
import com.leandro.news.domain.toJson
import com.leandro.news.formatters.toFormattedString

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeadlineItem(
    modifier : Modifier = Modifier,
    article: Article,
    openDetail: (String) -> Unit

){
    Card(
        modifier = modifier,
        onClick = {
            article.toJson()?.let {
                openDetail(it)
            }
        },
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = article.author,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.h2,
                    color = Color.Gray
                )

                article.published?.toFormattedString()?.let { date ->
                    Text(
                        text = date,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.h2,
                        color = Color.Gray
                    )

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ){

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(article.image)
                        .crossfade(true)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(150.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Banner image"
                )

                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = article.title
                    ,
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.onSecondary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        text = article.description,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray
                    )
                }

            }
        }
    }
}
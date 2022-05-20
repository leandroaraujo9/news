package com.leandro.news.presentation.headline

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leandro.news.domain.model.Article
import com.leandro.news.formatters.toFormattedString
import com.leandro.news.presentation.components.BackButton
import com.leandro.news.ui.theme.fonts

object HeadlineDetailTestTags {
    const val BACK_BUTTON = "BACK_BUTTON"
    const val IMAGE = "IMAGE"
    const val AUTHOR_TEXT = "AUTHOR_TEXT"
    const val DATE_TEXT = "DATE_TEXT"
    const val TITLE_TEXT = "TITLE_TEXT"
    const val DESCRIPTION_TEXT = "DESCRIPTION_TEXT"
    const val CONTENT_TEXT = "CONTENT_TEXT"
    const val READ_MORE_TEXT = "READ_MODE_TEXT"
}

@Composable
fun HeadlineDetailScreen(
    article: Article,
    navController: NavController
) {
    val scroll = rememberScrollState(0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scroll)
    ) {
        BackButton(
            navController = navController,
            modifier = Modifier.testTag(HeadlineDetailTestTags.BACK_BUTTON)
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article.image)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .fillMaxWidth()
                .testTag(HeadlineDetailTestTags.IMAGE),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Banner image"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.testTag(HeadlineDetailTestTags.AUTHOR_TEXT),
                text = article.author,
                fontSize = 12.sp,
                style = MaterialTheme.typography.h2,
                color = Color.Gray
            )

            article.published?.toFormattedString()?.let { date ->
                Text(
                    modifier = Modifier.testTag(HeadlineDetailTestTags.DATE_TEXT),
                    text = date,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.h2,
                    color = Color.Gray
                )
            }
        }

        Text(
            modifier = Modifier.testTag(HeadlineDetailTestTags.TITLE_TEXT),
            text = article.title,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onSecondary,
            fontSize = 28.sp
        )

        Text(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .testTag(HeadlineDetailTestTags.DESCRIPTION_TEXT),
            text = article.description,
            fontSize = 12.sp,
            style = MaterialTheme.typography.body2,
            color = Color.Gray
        )

        Text(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .testTag(HeadlineDetailTestTags.CONTENT_TEXT),
            text = article.content,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onSecondary,
            fontSize = 18.sp
        )

        val readMoreAnnotated = getReadMoreAnnotatedString()
        val context = LocalContext.current

        ClickableText(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .testTag(HeadlineDetailTestTags.READ_MORE_TEXT),
            text = readMoreAnnotated,
            style = MaterialTheme.typography.body1,
            onClick = {
                ContextCompat.startActivity(
                    context,
                    Intent(Intent.ACTION_VIEW, Uri.parse(article.url)),
                    null
                )
            }
        )

    }
}

@Composable
private fun getReadMoreAnnotatedString() = buildAnnotatedString {
    withStyle(
        style = SpanStyle(
            color = MaterialTheme.colors.onSecondary,
            fontFamily = fonts,
            fontWeight = FontWeight.Light
        )
    ) {
        append("Click here to")
    }
    withStyle(
        style = SpanStyle(
            color = Blue,
            fontFamily = fonts,
            fontWeight = FontWeight.Light
        )
    ) {
        append(" read more")
    }
}
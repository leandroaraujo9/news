package com.leandro.news.data.response

import com.leandro.news.data.ArticleDto
import com.squareup.moshi.Json

data class HeadlineResponse(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int,
    @field:Json(name = "message")  val errorMessage: String?
) {
}
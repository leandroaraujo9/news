package com.leandro.news.domain.model

import java.util.*

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val image: String,
    val published: Date?,
    val content: String
)
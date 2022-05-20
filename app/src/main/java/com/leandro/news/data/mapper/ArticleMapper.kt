package com.leandro.news.data.mapper

import com.leandro.news.data.ArticleDto
import com.leandro.news.domain.model.Article
import com.leandro.news.formatters.toDate

fun List<ArticleDto>.toDomainArticles(): List<Article> =
        this.map{
            Article(
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                image = it.urlToImage,
                published = it.publishedAt.toDate(),
                content = it.content
            )
        }
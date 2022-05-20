package com.leandro.news.domain.repository

import com.leandro.news.BuildConfig
import com.leandro.news.base.Resource
import com.leandro.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface HeadlineRepository {
    fun getTopHeadlines(source: String? = BuildConfig.SOURCE) : Flow<Resource<List<Article>>>
}
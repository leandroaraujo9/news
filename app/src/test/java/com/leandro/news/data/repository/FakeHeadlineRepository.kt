package com.leandro.news.data.repository

import com.leandro.news.base.Resource
import com.leandro.news.domain.model.Article
import com.leandro.news.domain.repository.HeadlineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHeadlineRepository : HeadlineRepository {
    override fun getTopHeadlines(source: String?): Flow<Resource<List<Article>>> = flow {}
}
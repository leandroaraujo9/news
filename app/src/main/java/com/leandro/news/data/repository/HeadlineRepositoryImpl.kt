package com.leandro.news.data.repository


import com.leandro.news.base.Resource
import com.leandro.news.data.remote.HeadlineApi
import com.leandro.news.domain.model.Article
import com.leandro.news.domain.repository.HeadlineRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext

class HeadlineRepositoryImpl(
    private val api: HeadlineApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : HeadlineRepository{

    override fun getTopHeadlines(source: String?): Flow<Resource<List<Article>>> = channelFlow {
        withContext(ioDispatcher){
        }
    }

}
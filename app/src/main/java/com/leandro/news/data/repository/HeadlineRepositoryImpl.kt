package com.leandro.news.data.repository

import com.leandro.news.base.Resource
import com.leandro.news.data.mapper.toDomainArticles
import com.leandro.news.data.remote.HeadlineApi
import com.leandro.news.domain.model.Article
import com.leandro.news.domain.repository.HeadlineRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HeadlineRepositoryImpl @Inject constructor(
    private val api: HeadlineApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : HeadlineRepository {

    companion object {
        const val SUCCESS = "ok"
        const val ERROR = "error"
    }

    override fun getTopHeadlines(source: String?): Flow<Resource<List<Article>>> = channelFlow {
        withContext(ioDispatcher) {
            try {
                send(Resource.Loading(true))
                val result = api.getTopHeadlines(source)

                when (result.status) {
                    SUCCESS -> {
                        send(Resource.Success(result.articles.toDomainArticles()))
                    }
                    ERROR -> {
                        send(Resource.Error(result.errorMessage ?: "Unexpected Error"))
                    }
                }
            } catch (ex: IOException) {
                send(Resource.Error(ex.localizedMessage ?: "Unexpected Error"))
            } catch (ex: HttpException) {
                send(Resource.Error(ex.localizedMessage ?: "Unexpected Error"))
            } finally {
                send(Resource.Loading(false))
            }
        }
    }


}
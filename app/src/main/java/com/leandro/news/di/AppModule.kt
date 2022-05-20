package com.leandro.news.di

import com.leandro.news.BuildConfig
import com.leandro.news.data.remote.HeadlineApi
import com.leandro.news.data.repository.HeadlineRepositoryImpl
import com.leandro.news.domain.repository.HeadlineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNewsApi(): HeadlineApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val url = chain
                            .request()
                            .url
                            .newBuilder()
                            .addQueryParameter("apiKey", BuildConfig.API_KEY)
                            .build()
                        chain.proceed(chain.request().newBuilder().url(url).build())
                    }
                    .build()
            )
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideHeadlineRepositoryImpl(api: HeadlineApi) : HeadlineRepository {
        return HeadlineRepositoryImpl(api)
    }

}
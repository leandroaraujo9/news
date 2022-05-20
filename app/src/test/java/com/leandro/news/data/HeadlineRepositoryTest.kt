package com.leandro.news.data

import com.google.common.truth.Truth.assertThat
import com.leandro.news.base.Resource
import com.leandro.news.data.remote.HeadlineApi
import com.leandro.news.data.repository.HeadlineRepositoryImpl
import com.leandro.news.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class HeadlineRepositoryTest {

    private lateinit var headlineRepository: HeadlineRepositoryImpl

    private lateinit var server: MockWebServer

    private fun providesApi() = Retrofit.Builder()
        .baseUrl(server.url(""))
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(HeadlineApi::class.java)

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        server = MockWebServer()
        server.start()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        server.shutdown()
    }

    @Test
    fun  `should make a request with default source and return success`() = runTest {
        server.enqueue(MockResponse().setBody(body))
        val api = providesApi()

        headlineRepository = HeadlineRepositoryImpl(api)
        lateinit var success : Resource<List<Article>>
        headlineRepository.getTopHeadlines().take(2).collect {
            success = it
        }

        val request = server.takeRequest(1, TimeUnit.MILLISECONDS)
        advanceUntilIdle()

        assertThat(request?.path).isEqualTo("/top-headlines?sources=axios")
        assertThat(request?.method).isEqualTo("GET")
        assertThat(success.data).isNotEmpty()
    }

    @Test
    fun  `should make a request with another source and return success`() = runTest {
        server.enqueue(MockResponse().setBody(body))
        val api = providesApi()
        headlineRepository = HeadlineRepositoryImpl(api)

        lateinit var success : Resource<List<Article>>
        val source = "bbc-news"
        headlineRepository.getTopHeadlines(source).take(2).collect {
            success = it
        }

        val request = server.takeRequest(1, TimeUnit.MILLISECONDS)
        advanceUntilIdle()

        assertThat(request?.path).isEqualTo("/top-headlines?sources=axios")
        assertThat(request?.method).isEqualTo("GET")
        assertThat(success.data).isNotEmpty()
    }

    @Test
    fun  `should make a request with malformed body and return error`() = runTest {
        server.enqueue(MockResponse().setBody(""))
        val api = providesApi()
        headlineRepository = HeadlineRepositoryImpl(api)

        lateinit var error : Resource<List<Article>>
        val source = "bbc-news"
        headlineRepository.getTopHeadlines(source).take(2).collect {
            error = it
        }

        val request = server.takeRequest(1, TimeUnit.MILLISECONDS)
        advanceUntilIdle()

        assertThat(request?.path).isEqualTo("/top-headlines?sources=axios")
        assertThat(request?.method).isEqualTo("GET")
        assertThat(error).isInstanceOf(Resource.Error::class.java)
    }


    val body = "{\n" +
            "  \"status\": \"ok\",\n" +
            "  \"totalResults\": 10,\n" +
            "  \"articles\": [\n" +
            "    {\n" +
            "      \"source\": {\n" +
            "        \"id\": \"bbc-news\",\n" +
            "        \"name\": \"BBC News\"\n" +
            "      },\n" +
            "      \"author\": \"BBC News\",\n" +
            "      \"title\": \"Qianlong-period Chinese vase, kept in kitchen, fetches almost £1.5m\",\n" +
            "      \"description\": \"The blue-glazed vase had been estimated to sell at auction for between £100,000-£150,000.\",\n" +
            "      \"url\": \"http://www.bbc.co.uk/news/uk-england-berkshire-61479493\",\n" +
            "      \"urlToImage\": \"https://ichef.bbci.co.uk/news/1024/branded_news/8B0E/production/_124789553_vase1.jpg\",\n" +
            "      \"publishedAt\": \"2022-05-18T21:52:20.7251121Z\",\n" +
            "      \"content\": \"Image caption, The blue-glazed, silver and gilt vase was created for the court of the Qianlong Emperor\\r\\nAn \\\"extremely rare\\\" 18th Century Chinese vase bought in the 1980s for a few hundred pounds and … [+1886 chars]\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"source\": {\n" +
            "        \"id\": \"bbc-news\",\n" +
            "        \"name\": \"BBC News\"\n" +
            "      },\n" +
            "      \"author\": \"BBC News\",\n" +
            "      \"title\": \"Trump urges Oz to declare victory in cliffhanger Senate vote\",\n" +
            "      \"description\": \"But the too-close-to-call Republican primary in Pennsylvania is likely to go to a recount.\",\n" +
            "      \"url\": \"http://www.bbc.co.uk/news/world-us-canada-61497963\",\n" +
            "      \"urlToImage\": \"https://ichef.bbci.co.uk/news/1024/branded_news/177BE/production/_124809169_gettyimages-1240498183.jpg\",\n" +
            "      \"publishedAt\": \"2022-05-18T21:52:16.1459927Z\",\n" +
            "      \"content\": \"Image source, Getty Images\\r\\nImage caption, Donald Trump at a campaign rally for Mehmet Oz on 6 May\\r\\nFormer President Donald Trump has urged celebrity doctor Mehmet Oz to declare victory in Tuesday's … [+2706 chars]\"\n" +
            "    }]\n" +
            "}"
}
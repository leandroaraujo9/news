package com.leandro.news.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leandro.news.domain.model.Article
import com.leandro.news.formatters.toDate
import com.leandro.news.presentation.headline.HeadlineDetailScreen
import com.leandro.news.presentation.headline.HeadlineDetailTestTags
import com.leandro.news.ui.navigation.Screen
import com.leandro.news.ui.theme.NewsTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HeadlineDetailScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockArticle = Article(
        author = "BBC NEWS",
        title = "Title 1",
        description = "Description 1",
        url = "https://www.cbsnews.com",
        image = "",
        published = "2022-05-18T20:05:00Z".toDate(),
        content = "Content 1"
    )

    @Before
    fun setUp() {
        hiltRule.inject()
        composeTestRule.setContent {
            val navController = rememberNavController()
            NewsTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.HeadlineDetail.route
                ){
                    composable(route = Screen.HeadlineDetail.route){
                        HeadlineDetailScreen(article = mockArticle, navController = navController)
                    }
                }
            }
        }
    }

    /*
    Not visible because the image doesn't have placeholder.
     */
    @Test
    fun image_isNotVisible() {
        //composeTestRule.onNodeWithTag(HeadlineDetailTestTags.IMAGE).assertIsNotDisplayed()
    }
//
//    @Test
//    fun author_isVisible() {
//        composeTestRule.onNodeWithTag(HeadlineDetailTestTags.AUTHOR_TEXT).assertIsDisplayed()
//        composeTestRule.onNodeWithText("BBC NEWS").assertExists()
//    }
//
//    @Test
//    fun date_isVisible() {
//        composeTestRule.onNodeWithTag(HeadlineDetailTestTags.DATE_TEXT).assertIsDisplayed()
//        composeTestRule.onNodeWithTag(HeadlineDetailTestTags.DATE_TEXT).assertTextContains("2022 - 20:05h", substring = true)
//    }
//
//    @Test
//    fun title_isVisible() {
//        composeTestRule.onNodeWithTag(HeadlineDetailTestTags.TITLE_TEXT).assertIsDisplayed()
//        composeTestRule.onNodeWithText("Title 1").assertExists()
//    }
//
//    @Test
//    fun description_isVisible() {
//        composeTestRule.onNodeWithTag(HeadlineDetailTestTags.DESCRIPTION_TEXT).assertIsDisplayed()
//        composeTestRule.onNodeWithText("Description 1").assertExists()
//    }
//
//    @Test
//    fun content_isVisible() {
//        composeTestRule.onNodeWithTag(HeadlineDetailTestTags.CONTENT_TEXT).assertIsDisplayed()
//        composeTestRule.onNodeWithText("Content 1").assertExists()
//    }
//
//    @Test
//    fun readMode_isVisible() {
//        composeTestRule.onNodeWithTag(HeadlineDetailTestTags.READ_MORE_TEXT).assertIsDisplayed()
//        composeTestRule.onNodeWithTag(HeadlineDetailTestTags.READ_MORE_TEXT).assertTextContains("Click here to read more")
//    }
}
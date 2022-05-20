package com.leandro.news.presentation.headline

sealed class HeadlineEvent {
    object Refresh: HeadlineEvent()
}
package com.leandro.news.formatters

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

@SuppressLint("SimpleDateFormat")
fun String.toDate() : Date? {
    val formatter = SimpleDateFormat(DATE_FORMAT)
    return formatter.parse(this.substring(0, 19))
}
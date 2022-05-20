package com.leandro.news.formatters

import java.text.SimpleDateFormat
import java.util.*


/**
 * Disclaimer : This extension class is far to be the ideal.
 *
 * I'm running out of time and needed to rush this part and raise the
 * minSdkVersion to 26.
 *
 */
const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
const  val STRING_FORMAT = "MM/dd/yyyy - HH:mm'h'"

fun String.toDate() : Date? {
    val formatter = SimpleDateFormat(DATE_FORMAT, Locale.US)
    return formatter.parse(this.substring(0, 19))
}

fun Date.toFormattedString() : String? {
    val simpleDate = SimpleDateFormat(STRING_FORMAT, Locale.US)
    return simpleDate.format(this)
}
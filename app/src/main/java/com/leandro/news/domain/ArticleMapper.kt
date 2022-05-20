package com.leandro.news.domain

import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import java.util.*

inline fun <reified T> String.toObject() : T? {
    val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()
    val jsonAdapter = moshi.adapter(T::class.java).lenient()
    return jsonAdapter.fromJson(this.replace("\$slash\$", "/"))
}

inline fun <reified T> T.toJson() : String? {
    val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()
    val jsonAdapter = moshi.adapter(T::class.java).lenient()
    return jsonAdapter.toJson(this).replace("/", "\$slash\$")
}
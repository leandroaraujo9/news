package com.leandro.news.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.leandro.news.R

val fonts = FontFamily(
    Font(R.font.nexa_trial_black),
    Font(R.font.nexa_trial_bold, weight = FontWeight.Bold),
    Font(R.font.nexa_trial_light, weight = FontWeight.Light),
    Font(R.font.nexa_trial_black, weight = FontWeight.Normal, style = FontStyle.Italic),
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontStyle = FontStyle.Italic,
        fontSize = 16.sp
    )
)
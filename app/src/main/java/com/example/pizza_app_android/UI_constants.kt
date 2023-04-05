package com.example.pizza_app_android

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val headerStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Bold,
    fontStyle = FontStyle.Italic,
    fontSize = 32.sp,
    color = Color.Black,
    textAlign = TextAlign.Center
)
val mediumHeader = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Bold,
    //fontStyle = FontStyle.Italic,
    fontSize = 26.sp,
    color = Color.Black,
)

val smallHeader = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Bold,
    //fontStyle = FontStyle.Italic,
    fontSize = 22.sp,
    color = Color.Black,
)


val titleStyle = TextStyle(
    fontSize = 36.sp,
    fontFamily = FontFamily.Monospace,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    fontStyle=FontStyle.Italic,
    color = Color.White,
)

val paragraphStyle = TextStyle(
    fontSize = 18.sp,
    fontFamily = FontFamily.Monospace,
    fontWeight = FontWeight.Bold,
    fontStyle=FontStyle.Italic,

)
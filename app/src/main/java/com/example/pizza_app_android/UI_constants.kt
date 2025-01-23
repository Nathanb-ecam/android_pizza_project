package com.example.pizza_app_android


import androidx.compose.material.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.provider.FontRequest



/*val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val headerfontFamily = FontFamily(
    Font(
        GoogleFont("roboto"), provider
    )
)

val textFontFamily = FontFamily(
    Font(
        GoogleFont("open sans"), provider
    )
)*/


val headerStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    //fontFamily = headerfontFamily,
    fontSize = 50.sp,
    color = Color.Black,
    textAlign = TextAlign.Center
)
val mediumHeader = TextStyle(
    //fontFamily = textFontFamily,
    fontWeight = FontWeight.Bold,
    //fontStyle = FontStyle.Italic,
    fontSize = 26.sp,
    color = Color.Black,
)

val smallHeader = TextStyle(
    //fontFamily = textFontFamily,
    fontWeight = FontWeight.Bold,
    //fontStyle = FontStyle.Italic,
    fontSize = 22.sp,
    color = Color.Black,
)


val titleStyle = TextStyle(
    fontSize = 36.sp,
    //fontFamily = textFontFamily,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    fontStyle=FontStyle.Italic,
    color = Color.White,
)

val paragraphStyle = TextStyle(
    fontSize = 18.sp,
    //fontFamily = textFontFamily,
    fontWeight = FontWeight.Bold,
    fontStyle=FontStyle.Italic,

)
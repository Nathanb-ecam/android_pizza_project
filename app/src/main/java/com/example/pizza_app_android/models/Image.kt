package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val type : String,
    val data : ByteArray,
)
package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
open class Product(
    val Id:Int,
    val Name: String="",
    val Cost : Float=0f,
)
{
}
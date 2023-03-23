package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val name: String,
    val price : Float,
) {

}
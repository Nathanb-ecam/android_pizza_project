package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
data class Drink(
    val name: String="",
    val price : Float=0f,
)

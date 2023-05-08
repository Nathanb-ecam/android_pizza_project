package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val token : String,
    val maxAge : String,
    val isAdmin:Boolean,
    val user_id:Int
)
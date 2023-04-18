package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val name : String,
    val password : String,
)
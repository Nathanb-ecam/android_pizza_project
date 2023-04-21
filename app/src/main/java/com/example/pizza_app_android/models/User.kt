package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val user_id:Int,
    val name : String,
    val password : String,
    val isAdmin :Boolean,
    val points: Int = 0,
    val token:String=""
)
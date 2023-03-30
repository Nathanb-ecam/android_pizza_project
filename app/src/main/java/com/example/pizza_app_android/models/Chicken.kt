package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable


@Serializable
class Chicken (private val chicken_id:Int,private val chicken_name:String,private val price:Float=10.0f) : Product(chicken_id,chicken_name,price)
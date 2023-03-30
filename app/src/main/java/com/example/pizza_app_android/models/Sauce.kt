package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable


@Serializable
data class Sauce (private val sauce_id:Int,private val sauce_name:String,private val price:Float=10.0f): Product(sauce_id,sauce_name,price)
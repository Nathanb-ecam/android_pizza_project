package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable


@Serializable
class Drink (private val drink_id:Int,private val drink_name:String,private  val price:Float=2.0f) : Product(drink_id,drink_name,price)
package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable
import java.sql.Blob

@Serializable
open class Product(
    val id:Int,
    val name: String="",
    val price : Float=0f,
    val image: Image,
    var desc:String=""
)
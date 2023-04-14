package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
open class OrderExtra(
    val idOrder:Int,
    val quantityExtra: Int=1,
    val idExtra : Int,

)
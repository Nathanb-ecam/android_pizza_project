package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
open class ElementOfOrder(
    val idOrder:Int,
    val idMenu:Int

    )
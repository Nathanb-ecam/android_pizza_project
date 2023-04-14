package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
data class Selection(
    val sauce: Product?=null,
    val drink: Product?=null,
    val pizza: Product?=null,
    val chicken: Product?=null,
    val price:Float=10f

) {

}
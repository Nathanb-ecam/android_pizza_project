package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val sauce: String="",
    val drink: String="",
    val pizza: String="",
    val chicken: String="",

) {

}
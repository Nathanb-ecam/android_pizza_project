package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
data class Formula(
    val name: String="Loading formulas",
    val price : Float=0f,
    val desc : String="",
    val drink_quantity: Int=0,
    val pizza_quantity: Int=0,
    val chicken_quantity: Int=0,
    val sauce_quantity: Int=0,
) {

}
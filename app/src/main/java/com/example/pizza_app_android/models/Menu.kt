package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val menu_id :Int,
    val menu_name:String,
    val idSauce: Int=0,
    val idChicken: Int=0,
    val idPizza: Int=0,
    val idDrink: Int=0,

) {

}
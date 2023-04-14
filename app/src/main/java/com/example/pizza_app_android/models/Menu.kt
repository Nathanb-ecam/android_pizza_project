package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val idSauce: Int?=null,
    val idDrink: Int?=null,
    val idPizza: Int?=null,
    val idChicken: Int?=null,

    ) {

}
package com.example.pizza_app_android.models

import kotlinx.serialization.Serializable

@Serializable
open class OrderExtra(
    val idOrder:Int,
    val idExtraDrink : Int? = null,
    val idExtraPizza : Int? = null,
    val idExtraChicken : Int? = null,
    val idExtraSauce : Int? = null,

)
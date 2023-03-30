package com.example.pizza_app_android.models

/*import kotlinx.serialization.Serializable


@Serializable*/
class Pizza(val pizza_id:Int, val pizza_name:String, val price:Float)
    : Product (pizza_id,pizza_name,price)
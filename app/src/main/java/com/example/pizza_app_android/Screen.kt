package com.example.pizza_app_android

sealed class Screen(val route:String) {
    object HomeScreen : Screen("home_screen")
    object MenuScreen : Screen("menu_screen")
    object DrinkScreen : Screen("drink_screen")
    object SauceScreen : Screen("sauce_screen")
    object PizzaScreen : Screen("pizza_screen")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(vararg args :String):String{
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}
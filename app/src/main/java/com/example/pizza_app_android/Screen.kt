package com.example.pizza_app_android

sealed class Screen(val route:String) {
    object HomeScreen : Screen("home")
    object FormulaScreen : Screen("formula")
    object DrinkScreen : Screen("drink")
    object SauceScreen : Screen("sauce")
    object PizzaScreen : Screen("pizza")
    object DetailScreen : Screen("detail")
    object LoginScreen : Screen("login")

    fun withArgs(vararg args :String):String{
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}
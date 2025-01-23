package com.example.pizza_app_android

sealed class Screen(val route:String) {
    object MenuScreen : Screen("menu")
    object DrinkScreen : Screen("drink")
    object SauceScreen : Screen("sauce")
    object PizzaScreen : Screen("pizza")
    object DetailScreen : Screen("detail")
    object LoginScreen : Screen("login")
    object RecapScreen : Screen("recap")

    fun withArgs(vararg args :String):String{
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }


    companion object {
        val routeToTitleMap: Map<String, String> by lazy {
            mapOf(
                MenuScreen.route to "Menus",
                DrinkScreen.route to "Boissons",
                SauceScreen.route to "Sauces",
                PizzaScreen.route to "Pizzas",
                DetailScreen.route to "Details",
                LoginScreen.route to "Login",
                RecapScreen.route to "Commande"
            )
        }


        fun getScreenTitle(route: String): String? {
            return routeToTitleMap[route]
        }
    }
}
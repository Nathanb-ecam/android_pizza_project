package com.example.pizza_app_android


import PizzaScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pizza_app_android.ui.app_screens.DetailScreen
import com.example.pizza_app_android.ui.app_screens.DrinkScreen
import com.example.pizza_app_android.ui.app_screens.MenuScreen
import com.example.pizza_app_android.ui.app_screens.SauceScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(route=Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(route=Screen.PizzaScreen.route){
            PizzaScreen(navController = navController)
        }
        composable(route=Screen.DetailScreen.route){
            DetailScreen()
        }
        //composable(
          //  route=Screen.DetailScreen.route+ "/{desc}",
            //arguments = listOf(
              //  navArgument("desc"){
                //    type = NavType.StringType
                  //  defaultValue = "Not found"
                //}
            //)
        //){entry->
          //  DetailScreen(description = entry.arguments?.getString("desc"))
        //}
        composable(route=Screen.DrinkScreen.route){
            DrinkScreen(navController = navController)
        }
        composable(route=Screen.SauceScreen.route){
            SauceScreen(navController = navController)
        }
        composable(route=Screen.MenuScreen.route){
            MenuScreen(navController = navController)
        }

    }
}
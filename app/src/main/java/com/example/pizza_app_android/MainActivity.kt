package com.example.pizza_app_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.pizza_app_android.models.BottomNavItem
import com.example.pizza_app_android.ui.theme.Pizza_app_androidTheme
import com.example.pizza_app_android.viewmodels.OrderViewModel
import com.example.pizza_app_android.viewmodels.RestaurantViewModel



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pizza_app_androidTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val orderViewModel : OrderViewModel = viewModel()
                val appViewModel : RestaurantViewModel = viewModel()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name="Menu",
                                    route = Screen.MenuScreen.route,
                                    icon_path = R.drawable.menu_icon
                                ),
                                BottomNavItem(
                                    name="Item's",
                                    route = Screen.PizzaScreen.route,
                                    icon_path = R.drawable.pizza_icon
                                ),
                                BottomNavItem(
                                    name="Drinks",
                                    route = Screen.DrinkScreen.route,
                                    icon_path = R.drawable.drink_icon
                                ),
                                BottomNavItem(
                                    name="Extras",
                                    route = Screen.ExtraScreen.route,
                                    icon_path = R.drawable.extra_icon
                                ),
                                BottomNavItem(
                                    name="Recap",
                                    route = Screen.RecapScreen.route,
                                    icon_path = R.drawable.cart_icon
                                ),
                            ),
                            navController = navController,
                            onItemClicked ={
                                navController.navigate(it.route)
                            }
                        )
                    },
                    topBar = {
                        TopNavigationBar()
                    }
                ){
                    Navigation(navController = navController,orderViewModel = orderViewModel,appViewModel = appViewModel )
                }
            }
        }
    }
}








@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Pizza_app_androidTheme {
        val navController = rememberNavController()
        val orderViewModel : OrderViewModel = viewModel()
        val appViewModel : RestaurantViewModel = viewModel()
        Navigation(navController = navController,orderViewModel = orderViewModel,appViewModel = appViewModel)
    }
}
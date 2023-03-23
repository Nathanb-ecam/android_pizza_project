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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pizza_app_androidTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val orderViewModel : OrderViewModel = viewModel()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name="Menu",
                                    route = Screen.FormulaScreen.route,
                                    icon_path = R.drawable.menu_icon
                                ),
                                BottomNavItem(
                                    name="Pizza's",
                                    route = Screen.PizzaScreen.route,
                                    icon_path = R.drawable.pizza_icon
                                ),
                                BottomNavItem(
                                    name="Drinks",
                                    route = Screen.DrinkScreen.route,
                                    icon_path = R.drawable.drink_icon
                                ),
                                BottomNavItem(
                                    name="Login",
                                    route = Screen.LoginScreen.route,
                                    icon_path = R.drawable.login_icon
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
                    Navigation(navController = navController,orderViewModel = orderViewModel )
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
        Navigation(navController = navController,orderViewModel = orderViewModel)
    }
}
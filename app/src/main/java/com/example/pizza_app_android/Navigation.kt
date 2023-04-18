package com.example.pizza_app_android


import PizzaScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.pizza_app_android.models.BottomNavItem
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.ui.app_screens.*
import com.example.pizza_app_android.viewmodels.OrderViewModel
import com.example.pizza_app_android.viewmodels.RestaurantViewModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

@Composable
fun Navigation(navController : NavHostController, orderViewModel: OrderViewModel, appViewModel: RestaurantViewModel){
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(route=Screen.PizzaScreen.route){
            PizzaScreen(navController = navController,appViewModel=appViewModel)
        }
        composable(route=Screen.ExtraScreen.route){
            ExtraScreen(navController = navController,appViewModel=appViewModel)
        }
/*        composable(route=Screen.DetailScreen.route){
            DetailScreen()
        }*/
        composable(
            route=Screen.DetailScreen.route+ "/{product}"+"/{productType}",
            arguments = listOf(
                navArgument("product"){
                    type = NavType.StringType
                    defaultValue = "Not found"
                },
                navArgument("productType"){
                    type = NavType.StringType
                    defaultValue = "Not found"
                }
            )
        ){
            var stringProduct = it.arguments?.getString("product")!!;
            val product = Json.decodeFromString<Product>(stringProduct);
            var stringProductType = it.arguments?.getString("productType")!!;
            val productType = Json.decodeFromString<ProductType>(stringProductType);
            DetailScreen(productType,product,appViewModel= appViewModel,orderViewModel=orderViewModel);
        }
        composable(route=Screen.DrinkScreen.route){
            DrinkScreen(navController = navController,appViewModel=appViewModel)
        }
        composable(route=Screen.SauceScreen.route){
            SauceScreen(navController = navController,appViewModel=appViewModel)
        }
        composable(route=Screen.MenuScreen.route){
            MenuScreen(navController = navController,orderViewModel= orderViewModel,appViewModel=appViewModel)
        }
        composable(route=Screen.RecapScreen.route){
            RecapScreen(navController = navController,orderViewModel= orderViewModel,appViewModel=appViewModel)
        }
        composable(route=Screen.LoginScreen.route){
            LoginScreen(navController = navController,orderViewModel=orderViewModel)
        }

    }
}

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun TopNavigationBar(){
    TopAppBar(
        backgroundColor = Color.hsl(345f, 0.95f, 0.25f, 1f)
    ) {
/*        Image(
            painter=painterResource(R.drawable.logo_image),
            contentDescription = "logo",
            modifier= Modifier.fillMaxSize()

        )*/
        Text(
            text = "Pizza hut",
            style = titleStyle,
            modifier=Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClicked : (BottomNavItem)->Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    // if we are currently on the loginscreen, we don't want to have bottom bar
    if (backStackEntry.value?.destination?.route !=Screen.LoginScreen.route){
        BottomNavigation(
            modifier = Modifier,
            backgroundColor = Color.DarkGray,
            elevation = 5.dp
        ){
            items.forEach{item->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClicked(item) },
                    selectedContentColor = Color.hsl(345f, 0.95f, 0.25f, 1f),
                    unselectedContentColor = Color.Gray,
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon_path),
                            contentDescription = item.name
                        )
                    }
                )
            }
        }
    }

}
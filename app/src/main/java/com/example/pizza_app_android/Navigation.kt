package com.example.pizza_app_android


import PizzaScreen
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.pizza_app_android.ui.app_screens.*
import com.example.pizza_app_android.viewmodels.OrderViewModel
import com.example.pizza_app_android.viewmodels.RestaurantViewModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

@Composable
fun Navigation(navController : NavHostController, orderViewModel: OrderViewModel, appViewModel: RestaurantViewModel){
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(route=Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(route=Screen.PizzaScreen.route){
            PizzaScreen(navController = navController)
        }
/*        composable(route=Screen.DetailScreen.route){
            DetailScreen()
        }*/
        composable(
            route=Screen.DetailScreen.route+ "/{item}",
            arguments = listOf(
                navArgument("item"){
                    type = NavType.StringType
                    defaultValue = "Not found"
                }
            )
        ){
            var stringItem = it.arguments?.getString("item")!!;
            val product = Json.decodeFromString<Product>(stringItem);
            DetailScreen(product,appViewModel= appViewModel);
        }
        composable(route=Screen.DrinkScreen.route){
            DrinkScreen(navController = navController)
        }
        composable(route=Screen.SauceScreen.route){
            SauceScreen(navController = navController)
        }
        composable(route=Screen.MenuScreen.route){
            MenuScreen(navController = navController,orderViewModel= orderViewModel)
        }
        composable(route=Screen.LoginScreen.route){
            LoginScreen(navController = navController)
        }

    }
}

@Composable
fun TopNavigationBar(){
    TopAppBar(
        backgroundColor = Color.Red
    ) {
        Text(
            text = "Pizza hut",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier= Modifier.fillMaxWidth(),
            color = Color.White,

        )
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClicked : (BottomNavItem)->Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
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
                selectedContentColor = Color.Red,
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
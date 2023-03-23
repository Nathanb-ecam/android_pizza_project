package com.example.pizza_app_android


import PizzaScreen
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
import com.example.pizza_app_android.ui.app_screens.*

@Composable
fun Navigation(navController : NavHostController, orderViewModel: OrderViewModel){
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
            route=Screen.DetailScreen.route+ "/{desc}",
            arguments = listOf(
                navArgument("desc"){
                    type = NavType.StringType
                    defaultValue = "Not found"
                }
            )
        ){entry->
            DetailScreen(description = entry.arguments?.getString("desc")!!)
        }
        composable(route=Screen.DrinkScreen.route){
            DrinkScreen(navController = navController)
        }
        composable(route=Screen.SauceScreen.route){
            SauceScreen(navController = navController)
        }
        composable(route=Screen.FormulaScreen.route){
            FormulaScreen(navController = navController,orderViewModel= orderViewModel)
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
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
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
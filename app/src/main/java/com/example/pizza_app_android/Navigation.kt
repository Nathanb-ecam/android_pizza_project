package com.example.pizza_app_android


import PizzaScreen
import android.widget.RemoteViews.RemoteCollectionItems
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pizza_app_android.models.BottomNavItem
import com.example.pizza_app_android.ui.app_screens.DetailScreen
import com.example.pizza_app_android.ui.app_screens.DrinkScreen
import com.example.pizza_app_android.ui.app_screens.MenuScreen
import com.example.pizza_app_android.ui.app_screens.SauceScreen
import com.example.pizza_app_android.ui.app_screens.HomeScreen
@Composable
fun Navigation(navController : NavHostController){
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
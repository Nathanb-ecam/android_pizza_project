package com.example.pizza_app_android


import PizzaScreen
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.pizza_app_android.ui.theme.MyPalette
import com.example.pizza_app_android.viewmodels.OrderViewModel
import com.example.pizza_app_android.viewmodels.RestaurantViewModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import java.util.Locale

@Composable
fun Navigation(navController : NavHostController, orderViewModel: OrderViewModel, appViewModel: RestaurantViewModel){
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(route=Screen.PizzaScreen.route){
            PizzaScreen(navController = navController,appViewModel=appViewModel)
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
            DetailScreen(productType,product,orderViewModel=orderViewModel, navController = navController);
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




@Composable
fun TopNavigationBar(navController : NavController){
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Extract the current route safely
    val currentRoute = backStackEntry?.destination?.route ?: return

    val currentTitle = Screen.getScreenTitle(currentRoute) ?: ""

    if (currentRoute != Screen.LoginScreen.route) {
        TopAppBar(
            backgroundColor = MyPalette.LigthGrayBackground
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val isDetailScreen = currentRoute.startsWith(Screen.DetailScreen.route)

                if (isDetailScreen) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MyPalette.PrimaryColor
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(48.dp))
                }

                // Title (Centered)
                Text(
                    text = currentTitle,
                    color = MyPalette.textBlack,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(48.dp))
            }
        }
    }

}

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier.background(MyPalette.LigthGrayBackground),
    onItemClicked : (BottomNavItem)->Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    // if we are currently on the loginscreen, we don't want to have bottom bar
    if (backStackEntry.value?.destination?.route != Screen.LoginScreen.route){
        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MyPalette.LigthGrayBackground,
            elevation = 5.dp
        ){
            items.forEach{item->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClicked(item) },
                    selectedContentColor = MyPalette.PrimaryColor,
                    unselectedContentColor = MyPalette.LightGray,
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
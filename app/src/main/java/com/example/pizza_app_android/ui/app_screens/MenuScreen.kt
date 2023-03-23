package com.example.pizza_app_android.ui.app_screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.pizza_app_android.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.RestaurantViewModel
import com.example.pizza_app_android.Screen
import com.example.pizza_app_android.models.Menu

@Composable
fun MenuScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel = viewModel()
){
    //val uiState by appViewModel.uiState.collectAsState()
    //appViewModel.getPizzas()
    //appViewModel.update()
    //Text(text="Pizza Hut")
/*    TextButton(onClick = { navController.navigate(Screen.PizzaScreen.route) }) {
        Text(text = "Text Button")
    }*/

    val menus = listOf<Menu>(
        Menu("The box",18f),
        Menu("Two pizza",26f),
        Menu("The chicken",16f),
        Menu("The box",18f)
    )

    Column{
        Text(text="Our menu's",fontSize = 32.sp)
        MenuList(menus)
    }



}
@Composable
fun MenuList(menus : List<Menu>){
    Scaffold {paddingValues->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues
        ){
            items(menus) { menu ->
                MenuCard(menu)
            }
        }
    }


}

@Composable
fun MenuCard(menu: Menu, modifier: Modifier = Modifier){
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp, backgroundColor = Color.LightGray){
        Box(modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .align(alignment = Alignment.Center)){
                Text(
                    text = menu.name ,
                    style = TextStyle(fontSize = 24.sp),
                    modifier = Modifier.padding(8.dp),
                    fontSize = 18.sp
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = menu.price.toString(),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
                Text(text=menu.price.toString(), fontSize = 18.sp, textAlign = TextAlign.End)
            }

        }
    }
}



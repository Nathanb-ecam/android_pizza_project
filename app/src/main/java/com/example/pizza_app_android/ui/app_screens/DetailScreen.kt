package com.example.pizza_app_android.ui.app_screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizza_app_android.RestaurantViewModel
import com.example.pizza_app_android.Screen
import com.example.pizza_app_android.models.Drink
import com.example.pizza_app_android.models.Formula
import com.example.pizza_app_android.models.Pizza

@Composable
fun DetailScreen(
    formula: Formula,
    appViewModel: RestaurantViewModel = viewModel()
){
    val uiState by appViewModel.uiState.collectAsState()
//"chickens","sauce",
    appViewModel.getPizzas()
    appViewModel.getDrinks()
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            Text(text=formula.name, fontSize = 32.sp)
            PizzaSection(formula = formula,uiState.pizzas)
            //DrinkSection(formula = formula,uiState.drinks)
        }
    }
}


@Composable
fun PizzaSection(formula: Formula,pizzas: List<Pizza>){
    Log.i("Details",formula.name)
    for(i in 1..formula.pizza_quantity ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            PizzaList(pizzas)
        }
    }

}

@Composable
fun PizzaList(pizzas: List<Pizza>){
    Column{
        Text(text="Pizza")
        LazyRow{
            items(pizzas){comp->
                PizzaCard(comp)
            }
        }
    }


}


@Composable
fun PizzaCard(pizza: Pizza){
    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp, backgroundColor = Color.LightGray){
        Box(modifier = Modifier
            .height(150.dp)
            .width(150.dp)) {
            Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pizza.name ,
                    fontSize = 24.sp,
                    //navController.navigate(Screen.DetailScreen.withArgs(pizza.name))
                )
                Text(text=pizza.price.toString(),fontSize =20.sp)
            }

        }
    }
}



/*@Composable
fun DrinkSection(formula: Formula,drinks : List<Drink>){
    for(i in 0..formula.drink_quantity ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            ComponentList(formula = formula,drinks,index = i)
        }
    }
}*/



package com.example.pizza_app_android.ui.app_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizza_app_android.headerStyle
import com.example.pizza_app_android.viewmodels.RestaurantViewModel
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.paragraphStyle
import com.example.pizza_app_android.viewmodels.OrderViewModel


@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun DetailScreen(
    productType: ProductType,
    product: Product,
    appViewModel: RestaurantViewModel,
    orderViewModel : OrderViewModel
){
    val uiState by appViewModel.uiState.collectAsState()
//"chickens","sauce",
    appViewModel.getPizzas()
    appViewModel.getDrinks()
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            Text(text="${productType.name} ${product.name}", style = headerStyle)
            //Text(text=product.name)
            Text(text="${product.price} euros",style= paragraphStyle)
            if(product.desc!=""){
                Text(text=product.desc,style= paragraphStyle)
            }
            //DrinkSection(formula = formula,uiState.drinks)
        }
        Button(
            modifier=Modifier.align(alignment = Alignment.Center),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.hsl(345f, 0.95f, 0.25f, 1f),
                contentColor = Color.White
            ),
            onClick = {
                orderViewModel.addExtra(productType,product);
                orderViewModel.showOrderContent();
            }
        ){
            Text(text="Ajouter à la commande",style= paragraphStyle)
        }
    }
}


@Composable
fun PizzaSection(product: Product, pizzas: List<Product>){
    //Log.i("Details",menu.name)
/*    for(i in 1..menu.pizza_quantity ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            PizzaList(pizzas)
        }
    }*/

}

@Composable
fun PizzaList(pizzas: List<Product>){
    Column{
        Text(text="Item")
        LazyRow{
            items(pizzas){comp->
                PizzaCard(comp)
            }
        }
    }


}


@Composable
fun PizzaCard(pizza: Product){
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



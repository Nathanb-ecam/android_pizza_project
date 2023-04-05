package com.example.pizza_app_android.ui.app_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import androidx.navigation.NavController
import com.example.pizza_app_android.*
import com.example.pizza_app_android.models.Menu
import com.example.pizza_app_android.viewmodels.RestaurantViewModel
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.viewmodels.OrderViewModel



@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun RecapScreen(
    navController: NavController,
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
        Column(modifier= Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            Text(text="Votre commande", style = headerStyle)
            val menus = orderViewModel.getOrderMenus()
            val extras = orderViewModel.getOrderExtras()
            Column(modifier = Modifier
                .height(450.dp)
                .verticalScroll(rememberScrollState())){
                if(menus.isNotEmpty()){
                    Text(text="Menus :",style= mediumHeader)
                    menus.forEach{
                        Card(backgroundColor = Color.LightGray, modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()){
                            Column(modifier=Modifier.padding(2.dp)){
                                Text(text="Boisson : ${it.drink}",style= paragraphStyle)
                                Text(text="Sauce : ${it.sauce}",style= paragraphStyle)
                                Text(text="Pizza : ${it.pizza}",style= paragraphStyle)
                                Text(text="Chicken : ${it.chicken}",style= paragraphStyle)
                            }
                        }
                    }
                }
                if(extras.isNotEmpty()){
                    Text(text="Extra's :",style= mediumHeader)
                    for((type,products)in extras){
                        Text(text=type.name,style= smallHeader)
                        Card(backgroundColor = Color.LightGray, modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()){
                            Column(modifier=Modifier.padding(6.dp)){

                                products.forEach{
                                    Text(text=it.name,style= paragraphStyle)
                                }
                            }
                        }
                    }
                }
            }
            Box(modifier=Modifier.fillMaxWidth(),contentAlignment = Alignment.Center){
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.hsl(345f, 0.95f, 0.25f, 1f),
                        contentColor = Color.White
                    ),
                    onClick = {
                        //orderViewModel.sendOrder();
                    }
                ){
                    Text(text="Envoyer la commande",style= paragraphStyle)
                }
            }
        }
    }
}


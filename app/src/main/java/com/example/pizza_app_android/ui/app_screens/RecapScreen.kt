package com.example.pizza_app_android.ui.app_screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pizza_app_android.*
import com.example.pizza_app_android.ui.theme.MyPalette
import com.example.pizza_app_android.viewmodels.RestaurantViewModel
import com.example.pizza_app_android.viewmodels.OrderViewModel



@SuppressLint("DefaultLocale")
@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun RecapScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel,
    orderViewModel : OrderViewModel
){
    val uiState by appViewModel.uiState.collectAsState()
    val orderUIState by orderViewModel.uiState.collectAsState()
//"chickens","sauce",
    appViewModel.getPizzas()
    appViewModel.getDrinks()
    Surface(
        modifier = Modifier.fillMaxSize().background(MyPalette.LightBackGround)
    ){
        Column(modifier= Modifier
            .padding(8.dp)
            .fillMaxSize()) {

            val menus = orderUIState.orderUISelection;
            val extras = orderViewModel.getOrderExtras()
            Column(modifier = Modifier
                //.height(500.dp)
                .fillMaxHeight(0.8f)
                .verticalScroll(rememberScrollState())){
                if(menus.isNotEmpty()){
                    Text(text="Menus :",style= mediumHeader)
                    menus.forEachIndexed { index, it ->
                        Card(backgroundColor = Color.LightGray, modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()){
                            Column(modifier=Modifier.padding(2.dp)){
                                Text(text="Boisson : ${it.drink?.name}",style= paragraphStyle)
                                Text(text="Sauce : ${it.sauce?.name}",style= paragraphStyle)
                                Text(text="Pizza : ${it.pizza?.name}",style= paragraphStyle)
                                Text(text="Chicken : ${it.chicken?.name}",style= paragraphStyle)
                                Text(text="${it.price} $",style= paragraphStyle)
                                Button(onClick={orderViewModel.removeMenuFromOrder(index);}){Text(text="Supprimer")}
                            }
                        }
                    }
                }
                if(extras.isNotEmpty()){
                    Text(text="Extra's :",style= mediumHeader)
                    for((type,products)in extras){
                        Text(text=type.name,style= paragraphStyle)
                        Card(backgroundColor = Color.LightGray, modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()){
                            Column(modifier=Modifier.padding(6.dp)){
                                products.forEach{
                                    Row( modifier= Modifier.fillMaxWidth(),horizontalArrangement= Arrangement.SpaceBetween){
                                        Text(text=it.name,style= paragraphStyle)
                                        Text(text=String.format("%.2f", it.price),style= paragraphStyle)
                                        //Button(onClick=orderViewModel.removeExtra(it.))

                                    }
                                }
                            }
                        }
                    }
                }
            }
            Box(modifier=Modifier.fillMaxWidth(),contentAlignment = Alignment.Center){
                Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically){
                    Text(text="Total : ${orderViewModel.orderTotal()}",style= paragraphStyle)
                    val context = LocalContext.current
                    Button(
                        modifier=Modifier.wrapContentWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.hsl(345f, 0.95f, 0.25f, 1f),
                            contentColor = Color.White
                        ),
                        onClick = {
                            orderViewModel.sendOrder();
                            Toast.makeText(context, "Commande envoyée", Toast.LENGTH_SHORT).show()

                        }
                    ){
                        Text(text="Envoyer la commande")
                    }
                }
            }
        }
    }
}


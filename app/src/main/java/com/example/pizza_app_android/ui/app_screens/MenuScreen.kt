package com.example.pizza_app_android.ui.app_screens


import android.content.ClipData
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.OrderViewModel
import com.example.pizza_app_android.RestaurantViewModel
import com.example.pizza_app_android.Screen
import com.example.pizza_app_android.models.Product

import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Composable
fun MenuScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel = viewModel(),
    orderViewModel : OrderViewModel = viewModel()
){
    //val uiState by appViewModel.uiState.collectAsState()
    //appViewModel.getPizzas()
    //appViewModel.update()
    //Text(text="Item Hut")
/*    TextButton(onClick = { navController.navigate(Screen.PizzaScreen.route) }) {
        Text(text = "Text Button")
    }*/
    val uiState by appViewModel.uiState.collectAsState()
    appViewModel.getDrinks()
    appViewModel.getPizzas()

    Column{
        Text(text="Create your menu",fontSize = 32.sp)
        Column(modifier=Modifier.fillMaxSize()){
            ProductsList(productType = "Drink",uiState.drinks)
            ProductsList(productType = "Pizza",uiState.pizzas)
        }


        //DrinkList(uiState.drinks,navController = navController)

    }



}
@Composable
fun ProductsList(productType :String,products : List<Product>, orderViewModel: OrderViewModel = viewModel()){
    val suggestions = products
    var selectedItem:Product=Product() ;

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = productType)
        LazyRow(
            modifier = Modifier.height(150.dp),//height(600.dp)
            contentPadding = PaddingValues(16.dp)
        ){
            items(suggestions) { suggestion ->
                Card(modifier = Modifier.padding(8.dp).height(100.dp).width(100.dp).clickable { selectedItem = suggestion;Log.i("Selected product",suggestion.name.toString()) },
                    elevation = 4.dp,
                    backgroundColor = Color.LightGray){
                    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                        Text(
                            text = AnnotatedString(suggestion.name) ,
                            style = TextStyle(fontSize = 20.sp),
                            //navController.navigate(Screen.DetailScreen.withArgs(pizza.name))

                        )
                        Text(text=suggestion.price.toString(),fontSize =18.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product,modifier: Modifier= Modifier){

}



























@Composable
fun MenuCard(product:Product, modifier: Modifier = Modifier, navController: NavController){
    val formulaJson = Json.encodeToString(product);
    //Log.i("JSON",formulaJson.toString())

    Card(modifier = Modifier
        .padding(8.dp)
        .clickable(onClick = { navController.navigate(Screen.DetailScreen.withArgs(formulaJson)) }), elevation = 4.dp, backgroundColor = Color.LightGray){
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = AnnotatedString("Menu") ,
                    style = TextStyle(fontSize = 20.sp),
                    //navController.navigate(Screen.DetailScreen.withArgs(pizza.name))
                )
                Text(text="something about the menu",fontSize =18.sp)
            }

/*            Column(modifier = Modifier
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
            }*/

        }
    }
}



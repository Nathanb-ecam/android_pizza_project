package com.example.pizza_app_android.ui.app_screens


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.Screen
import com.example.pizza_app_android.headerStyle
import com.example.pizza_app_android.mediumHeader
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.paragraphStyle
import com.example.pizza_app_android.viewmodels.OrderViewModel
import com.example.pizza_app_android.viewmodels.RestaurantViewModel

import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@OptIn(ExperimentalGraphicsApi::class)
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
    appViewModel.getChickens()
    appViewModel.getSauces()

    Surface(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()){
            Text(text="Menu", style = headerStyle)
            Column(modifier= Modifier
                //.height(500.dp)
                .fillMaxHeight(0.8f)
                .verticalScroll(rememberScrollState())){
                //Log.i("API",uiState.javaClass.declaredFields.size.toString())
                //val uiAttributes = uiState.javaClass.declaredFields.map{it.name};
                //Log.i("API",uiAttributes.toString())
                    //ProductsRowList(productType = it., suggestions = , orderViewModel = )
                orderViewModel.showOrderContent()
                val all = listOf<List<Product>>(uiState.drinks,uiState.sauces,uiState.chickens,uiState.pizzas)
                val allFetched = all.all{it.isNotEmpty()}

                if (allFetched){
                    ProductsRowList(productType = ProductType.Drink,uiState.drinks,orderViewModel)
                    ProductsRowList(productType = ProductType.Pizza,uiState.pizzas,orderViewModel)
                    ProductsRowList(productType = ProductType.Chicken,uiState.chickens,orderViewModel)
                    ProductsRowList(productType = ProductType.Sauce,uiState.sauces,orderViewModel)
                }
                else{
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.wrapContentSize()
                        )
                    }
                }
            }
            val context = LocalContext.current
            Button(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                onClick = {
                    val s = orderViewModel.getSelection()
                    Log.i("Order","selection")
                    //Log.i("Order","${s.chicken} ${s.sauce} ${it.drink} ${it.pizza}")

                    if(orderViewModel.getSelection().size==4){
                        orderViewModel.addMenuToOrder()
                        orderViewModel.showOrderContent()
                        Toast.makeText(context, "Menu ajouté à la commande", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.MenuScreen.route)

                    }
                    else{
                        Toast.makeText(context, "Veuiller sélectionner un élément pour chaque catégorie", Toast.LENGTH_LONG).show()
                    }

                          },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.hsl(345f, 0.95f, 0.25f, 1f),
                    contentColor = Color.White
                )){
                Text(text="Ajouter la selection")
            }
        }
    }
}



@Composable
fun ProductsRowList(productType :ProductType,suggestions : List<Product>, orderViewModel: OrderViewModel){
    var selectedIndex by rememberSaveable(){ mutableStateOf(-1) }
    if (suggestions.isNotEmpty()){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${productType.name} :",style= mediumHeader)
            LazyRow(
                modifier = Modifier.height(150.dp),//height(600.dp)
                contentPadding = PaddingValues(16.dp)
            ){
                itemsIndexed(suggestions) { index,suggestion ->
                    Card(modifier = Modifier
                        .padding(8.dp)
                        .height(100.dp)
                        .width(150.dp)
                        .clickable {
                            orderViewModel.applySelection(productType, suggestion);
                            Log.i("API", index.toString())
                            selectedIndex = index;
                        },
                        elevation = 4.dp,
                        backgroundColor = if (selectedIndex==index)Color.Gray else Color.LightGray){
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = AnnotatedString(suggestion.name) ,
                                style = paragraphStyle,


                                //navController.navigate(Screen.DetailScreen.withArgs(pizza.name))

                            )
                            Text(
                                text=suggestion.price.toString(),
                                style= paragraphStyle,

                            )
                        }
                    }
                }
            }
        }
    }

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



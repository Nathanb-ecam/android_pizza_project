package com.example.pizza_app_android.ui.app_screens


import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pizza_app_android.ErrorCard
import com.example.pizza_app_android.Screen
import com.example.pizza_app_android.TitleRow
import com.example.pizza_app_android.headerStyle
import com.example.pizza_app_android.mediumHeader
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.paragraphStyle
import com.example.pizza_app_android.ui.theme.MyPalette
import com.example.pizza_app_android.viewmodels.OrderViewModel
import com.example.pizza_app_android.viewmodels.RestaurantViewModel
import com.example.pizza_app_android.viewmodels.mock.MockRestaurantViewModel

import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun MenuScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel = viewModel(),
    orderViewModel : OrderViewModel = viewModel()
){

    val uiState by appViewModel.uiState.collectAsState()
    appViewModel.getDrinks()
    appViewModel.getPizzas()
    appViewModel.getChickens()
    appViewModel.getSauces()


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MyPalette.LightBackGround)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Content inside the scrollable column
            val all = listOf(uiState.drinks, uiState.sauces, uiState.chickens, uiState.pizzas)
            val allFetched = all.all { it.isNotEmpty() }

            if (allFetched) {
                // Display the product lists
                ProductsListWithSelectableItems(productType = ProductType.Drink, suggestions = uiState.drinks, orderViewModel = orderViewModel)
                ProductsListWithSelectableItems(productType = ProductType.Pizza, suggestions = uiState.pizzas, orderViewModel = orderViewModel)
                ProductsListWithSelectableItems(productType = ProductType.Chicken, suggestions = uiState.chickens, orderViewModel = orderViewModel)
                ProductsListWithSelectableItems(productType = ProductType.Sauce, suggestions = uiState.sauces, orderViewModel = orderViewModel)
            } else {
                // Display a loading indicator
                Box(
                    modifier = Modifier
                        .fillMaxWidth() // Use fillMaxWidth for proper alignment inside a scrollable layout
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.wrapContentSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp)) // Add spacing between content and button

            // Button at the bottom
            val context = LocalContext.current
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MyPalette.PrimaryColor,
                    contentColor = MyPalette.White
                ),
                elevation = null,
                onClick = {
                    val selection = orderViewModel.getSelection()
                    Log.i("Order", "Selection: $selection")

                    if (selection.size == 4) {
                        orderViewModel.addMenuToOrder()
                        orderViewModel.showOrderContent()
                        Toast.makeText(context, "Menu ajouté à la commande", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.MenuScreen.route)
                    } else {
                        Toast.makeText(context, "Veuillez sélectionner un élément pour chaque catégorie", Toast.LENGTH_LONG).show()
                    }
                },
            ) {
                Text(text = "Ajouter la sélection")
            }
        }
    }


}



@SuppressLint("UnrememberedMutableState")
@Composable
fun ProductsListWithSelectableItems(productType :ProductType,suggestions : List<Product>, orderViewModel: OrderViewModel){
    var selectedIndex by rememberSaveable(){ mutableStateOf(-1) }
    //var selectedProduct = mutableStateOf<Product?>(null)
    var selectedProduct by remember {mutableStateOf<Product?>(null)}


    if (suggestions.isNotEmpty()){
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            TitleRow(productType.name, selectedProduct?.name)
/*            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "${productType.name}", fontSize = 20.sp, color = MyPalette.textBlack, fontWeight = FontWeight.SemiBold)
                selectedProduct?.name?.let { Text(text= it, fontSize = 14.sp) }
            }*/
            Column(
                modifier = Modifier
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(10))
                    .clip(RoundedCornerShape(10))
                    .background(MyPalette.White)
            ) {
                suggestions.forEachIndexed { index, suggestion ->
                    ProductListItem(
                        orderViewModel,
                        productType,
                        suggestion,
                        index,
                        selectedIndex,
                        setSelectedItemIndexAndProduct = { newIndex ->
                            selectedIndex = newIndex
                            selectedProduct = suggestions[newIndex]
                        },
                    )
                    // Add a divider for all items except the last one
                    if (index != (suggestions.size - 1)) {
                        Box(
                            modifier = Modifier
                                .height(0.2.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(0.2.dp)
                                    .background(MyPalette.borderGray)
                            ) {}
                        }
                    }
                }
            }


        }
    }

}



@Composable
fun ProductListItem(
    orderViewModel: OrderViewModel? = null,
    productType: ProductType,
    suggestion: Product,
    itemIndex: Int, selectedIndex: Int? = null,
    onItemClicked: () -> Unit = {},
    setSelectedItemIndexAndProduct: (Int) -> Unit = {},
){
    val itemSelectedBackgroundColor = if (selectedIndex == itemIndex) MyPalette.PrimaryColor else MyPalette.White
    val itemSelectedTextColor = if (selectedIndex == itemIndex) MyPalette.White else MyPalette.textBlack

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background( color = itemSelectedBackgroundColor)
            .clickable {
                onItemClicked.invoke()
                orderViewModel?.applySelection(productType, suggestion);
                Log.i("API", itemIndex.toString())
                setSelectedItemIndexAndProduct.invoke(itemIndex)
            }
            .padding(vertical = 12.dp, horizontal = 8.dp)

        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text=AnnotatedString(suggestion.name), color = itemSelectedTextColor) //  color = itemBackgroundColor
        Text(text=suggestion.price.toString(),  color = itemSelectedTextColor)
    }
}






@Preview
@Composable
fun previewMenus(){
    val navController = rememberNavController()
    val appViewModel = MockRestaurantViewModel() as RestaurantViewModel
    MenuScreen(navController, appViewModel)
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

        }
    }
}



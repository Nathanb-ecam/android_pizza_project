package com.example.pizza_app_android.ui.app_screens


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.pizza_app_android.models.Formula
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Composable
fun FormulaScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel = viewModel(),
    orderViewModel : OrderViewModel = viewModel()
){
    //val uiState by appViewModel.uiState.collectAsState()
    //appViewModel.getPizzas()
    //appViewModel.update()
    //Text(text="Pizza Hut")
/*    TextButton(onClick = { navController.navigate(Screen.PizzaScreen.route) }) {
        Text(text = "Text Button")
    }*/
    val uiState by appViewModel.uiState.collectAsState()
    appViewModel.getFormulas()
    appViewModel.getDrinks()
    appViewModel.getPizzas()

    Column{
        Text(text="Our formula's",fontSize = 32.sp)
        FormulaList(uiState.formulas,navController = navController)
    }



}
@Composable
fun FormulaList(formulas : List<Formula>, navController: NavController){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp),
        contentPadding = PaddingValues(16.dp)
    ){
        items(formulas) { formula ->
            FormulaCard(formula,navController= navController)
        }
    }

}

@Composable
fun FormulaCard(formula: Formula, modifier: Modifier = Modifier, navController: NavController){
    val formulaJson = Json.encodeToString(formula);
    //Log.i("JSON",formulaJson.toString())

    Card(modifier = Modifier
        .padding(8.dp)
        .clickable(onClick = { navController.navigate(Screen.DetailScreen.withArgs(formulaJson)) }), elevation = 4.dp, backgroundColor = Color.LightGray){
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = AnnotatedString(formula.name) ,
                    style = TextStyle(fontSize = 20.sp),
                    //navController.navigate(Screen.DetailScreen.withArgs(pizza.name))
                )
                Text(text=formula.price.toString(),fontSize =18.sp)
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



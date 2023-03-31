import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.ProductList
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.viewmodels.RestaurantViewModel

@Composable
fun PizzaScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel = viewModel(),

    ){
    val uiState by appViewModel.uiState.collectAsState()
    //appViewModel.addPizza()
    //appViewModel.update()
    appViewModel.getPizzas()
    Surface(){
        Column {
            Text(text="Nos pizzas",fontSize=32.sp)
            //val pizzas = listOf<Pizza>(Pizza(0,"marg",12f),Pizza(0,"haw",12f))
            // Log.i("API",pizzas[0].name.toString())
            Log.i("API", "Out ProductList()");
/*            val pizzas = uiState.pizzas
            val products = mutableListOf<Product>()
            pizzas.forEach {
                products.add(Product(it.id,it.name,it.price))
            }*/
            ProductList(uiState.pizzas)
        }

    }


}



import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.ProductList
import com.example.pizza_app_android.models.Pizza
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.viewmodels.RestaurantViewModel

@Composable
fun PizzaScreen(navController: NavController, appViewModel: RestaurantViewModel = viewModel()){
    val uiState by appViewModel.uiState.collectAsState()
    appViewModel.getPizzas()
    Surface(
        modifier=Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
/*        val p:List<Product> = listOf<Pizza>(Pizza(100,"Pizza de test",12f))
        p.forEach{
            Log.i("Test",it.Name)
        }*/

        Column {
            Text(text="Nos pizzas",fontSize=32.sp)
            uiState.pizzas.forEach {
                Log.i("API"," Ui state ${it.pizza_id} ${it.pizza_name} ${it.price}")
            }
/*            val ls = listOf<Pizza>(Pizza(30,"margerite",12f))
            Log.i("API",ls.toString())*/
            //val p = uiState.pizzas as List<Product>
            ProductList(uiState.pizzas)
        }
    }


}



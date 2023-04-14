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
import com.example.pizza_app_android.headerStyle
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.viewmodels.RestaurantViewModel

@Composable
fun PizzaScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel = viewModel(),

    ){
    val uiState by appViewModel.uiState.collectAsState()
    //appViewModel.addPizza()
    //appViewModel.update()
    //appViewModel.getPizzas()
    Surface(){
        Column {
            Text(text="Nos pizzas",style= headerStyle)

            ProductList(ProductType.Pizza,uiState.pizzas,navController=navController)
/*            uiState.pizzas.forEach{
                Log.i("API","${it.name} ${it.desc}")
            }*/
        }

    }


}



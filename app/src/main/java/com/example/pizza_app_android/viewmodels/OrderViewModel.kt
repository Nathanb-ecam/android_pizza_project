package com.example.pizza_app_android.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pizza_app_android.models.Menu
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class OrderUiState(
    val no: MutableMap<String,String>
)

class OrderViewModel : ViewModel() {
    private val orderMenus  = mutableListOf<Menu>();
    private val selection:MutableMap<String,String> = mutableMapOf<String,String>(
/*        ProductType.Drink.name to "no drink",
        ProductType.Pizza.name to "no pizza",
        ProductType.Chicken.name to "no chicken",
        ProductType.Sauce.name to "no sauce"*/

    );
    private val _uiState = MutableStateFlow(OrderUiState(
        mutableMapOf<String,String>()
    ));
    val uiState : StateFlow<OrderUiState> = _uiState.asStateFlow();

    fun addMenuToOrder(){
        if (selection.isNotEmpty()){
            Log.i("Selection","Current selection"+selection.toString())
            orderMenus.add(Menu(0,"",0,1,1,0));
        }

    }

    fun applySelection(productType: ProductType,product: Product){
        selection[productType.name]= product.Name;
        //Log.i("Map", selection.toString())

    }

    fun showOrderContent(){
        Log.i("Order content",orderMenus.toString())
    }


}


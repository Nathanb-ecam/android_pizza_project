package com.example.pizza_app_android


import androidx.lifecycle.ViewModel
import com.example.pizza_app_android.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class OrderUiState(
    val order: MutableMap<String,String>
)

class OrderViewModel : ViewModel() {
    private val orderPizzas : MutableList<Product> = mutableListOf<Product>();
    private val _uiState = MutableStateFlow(OrderUiState(
        mutableMapOf<String,String>()
    ));
    val uiState : StateFlow<OrderUiState> = _uiState.asStateFlow();

/*    fun update(){
        val latestPizzas = Datasource().fetchPizzas();
        val currentState = _uiState.value;
        _uiState.value = currentState.copy(pizzas = latestPizzas);
    }*/

    fun addPizzaToOrder(pizza: Product){
        orderPizzas.add(pizza);
    }


}


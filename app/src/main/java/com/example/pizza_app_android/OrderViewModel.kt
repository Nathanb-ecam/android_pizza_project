package com.example.pizza_app_android


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_app_android.models.Drink
import com.example.pizza_app_android.models.Formula
import com.example.pizza_app_android.models.Order
import com.example.pizza_app_android.models.Pizza
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class OrderUiState(
    val order: Order
)

class OrderViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(
        listOf<Formula>(Formula()),
        listOf<Drink>(),
        listOf<Pizza>(),
    ));
    val uiState : StateFlow<UiState> = _uiState.asStateFlow();

    fun update(){
        val latestPizzas = Datasource().fetchPizzas();
        val currentState = _uiState.value;
        _uiState.value = currentState.copy(pizzas = latestPizzas);
    }

    fun getPizzas(){
        viewModelScope.launch {
            val latestPizzas = PizzaApi.retrofitService.getPizzas()
            val currentState = _uiState.value;
            try{
                _uiState.value = currentState.copy(pizzas = latestPizzas["pizzas"]!! as List<Pizza>);
            }
            catch (e:java.lang.Exception){

            }

            //Log.d("Result",stringResult)
        }
    }
}


package com.example.pizza_app_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_app_android.models.Pizza
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class UiState(
    val pizzas: List<Pizza>
)

class RestaurantViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(listOf<Pizza>(Pizza("En attente",0.0f))));
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
                _uiState.value = currentState.copy(pizzas = latestPizzas["pizzas"]!!);
            }
            catch (e:java.lang.Exception){

            }

            //Log.d("Result",stringResult)
        }
    }
}


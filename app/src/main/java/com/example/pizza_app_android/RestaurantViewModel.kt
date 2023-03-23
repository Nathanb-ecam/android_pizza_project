package com.example.pizza_app_android

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_app_android.models.Formula
import com.example.pizza_app_android.models.Pizza
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class UiState(
    val pizzas: List<Pizza>,
    val formulas :List<Formula>
)

class RestaurantViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(
                listOf<Pizza>(),
                listOf<Formula>(Formula()),
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
                _uiState.value = currentState.copy(pizzas = latestPizzas["pizzas"]!!);
            }
            catch (e:java.lang.Exception){

            }

            //Log.d("Result",stringResult)
        }
    }

    fun addPizza(pizza: Pizza){
        val call: Call<Pizza> = PizzaApi.retrofitService.addPizza(pizza)
        call.enqueue(object : Callback<Pizza?>{
            override fun onResponse(call: Call<Pizza?>, response: Response<Pizza?>) {
                Log.i( "API","Pizza added to api")
            }

            override fun onFailure(call: Call<Pizza?>, t: Throwable) {
                Log.i( "API","Error while trying to add pizza to api")
            }
        }

        )

    }


    fun getFormulas(){
        viewModelScope.launch {
            val latestFormulas = PizzaApi.retrofitService.getFormulas()
            val currentState = _uiState.value;
            try{
                _uiState.value = currentState.copy(formulas = latestFormulas["formulas"]!!);
            }
            catch (e:java.lang.Exception){

            }

            //Log.d("Result",stringResult)
        }
    }
}


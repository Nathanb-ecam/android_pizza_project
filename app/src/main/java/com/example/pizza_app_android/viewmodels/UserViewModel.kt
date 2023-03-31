package com.example.pizza_app_android.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pizza_app_android.PizzaApi
import com.example.pizza_app_android.models.Menu
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.pizza_app_android.OrderApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class UserUIState(
    val loggedIn: Boolean,
)

class UserViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UserUIState(true))

    val uiState : StateFlow<UserUIState> = _uiState.asStateFlow();

    fun authentificate(user : User): Boolean{
        Log.i("API",user.toString())
        var authorized = false;
        val call: Call<User> = OrderApi.retrofitService.authentify(user)
        call.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.i("API",responseBody.toString())
                    if (responseBody != null) {
                        if(user.name == responseBody.name && user.password == responseBody.password){
                            _uiState.value = _uiState.value.copy(loggedIn =true);
                        }
                    }
                    // handle the response body here
                } else {
                    // handle unsuccessful response
                    Log.i("API","invalid credentials")

                }
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                Log.i( "API","Error in authentification")
            }
        }
        )
        return authorized;
    }


}


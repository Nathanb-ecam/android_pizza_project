package com.example.pizza_app_android.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pizza_app_android.PizzaApi
import com.example.pizza_app_android.models.Login
import com.example.pizza_app_android.models.Token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class UserUIState(
    val loggedIn: Boolean,
    val errMessage : String? = null
)

class UserViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UserUIState(false))

    val uiState : StateFlow<UserUIState> = _uiState.asStateFlow();

    fun authentificate(orderViewModel: OrderViewModel,login:Login){

        val call: Call<Token> = PizzaApi.retrofitService.login(Login(login.name,login.password))
        call.enqueue(object : Callback<Token?> {
            override fun onResponse(call: Call<Token?>, response: Response<Token?>) {
                val currentState = _uiState.value;
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    //Log.i("API",responseBody.toString())
                    if (responseBody != null) {
                        orderViewModel.orderCredentials = Token(responseBody.token,responseBody.maxAge,responseBody.isAdmin,responseBody.user_id)
                        Log.i("Login","Api token : ${orderViewModel.orderCredentials.token}")

                        _uiState.value = currentState.copy(loggedIn = true);
                    }
                    // handle the response body here
                } else {
                    // handle unsuccessful response
                    _uiState.value = currentState.copy(errMessage = "Invalid username or password")
                    Log.i("Login","invalid credentials")

                }
            }

            override fun onFailure(call: Call<Token?>, t: Throwable) {
                Log.i( "API",t.toString())
                val currentState = _uiState.value
                _uiState.value = currentState.copy(errMessage = "Invalid credentials")
            }
        }
        )
    }


}

private fun <T> Call<T>.enqueue(any: Any) {

}


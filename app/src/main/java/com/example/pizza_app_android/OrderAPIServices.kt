package com.example.pizza_app_android


import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.User
import retrofit2.Retrofit
import retrofit2.http.GET
/*import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType*/
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "http://10.0.2.2:3000/api/"

private val retrofit = Retrofit.Builder()
    //.addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface OrderApiServices  {
    @POST("users")
    fun authentify(@Body user: User?): Call<User>



}

// singleton to limit high ressources use
object OrderApi {
    val retrofitService : OrderApiServices by lazy {
        retrofit.create(OrderApiServices::class.java)
    }
}
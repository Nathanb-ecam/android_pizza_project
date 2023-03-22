package com.example.pizza_app_android

import com.example.pizza_app_android.models.Pizza
import retrofit2.Retrofit
import retrofit2.http.GET
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.http.Path

private const val BASE_URL = "http://10.0.2.2:3000/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
    .baseUrl(BASE_URL)
    .build()


interface PizzaApiServices  {
    @GET("pizzas")
    suspend fun getPizzas():MutableMap<String,List<Pizza>>


    @GET("pizza/{pizza_id}")
    suspend fun getPizzaById(@Path("pizza_id") searchById:String):Pizza
}

// singleton to limit high ressources use
object PizzaApi {
    val retrofitService : PizzaApiServices by lazy {
        retrofit.create(PizzaApiServices::class.java)
    }
}
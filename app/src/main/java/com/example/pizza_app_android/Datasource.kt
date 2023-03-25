package com.example.pizza_app_android

import com.example.pizza_app_android.models.Product

class Datasource {
    fun fetchPizzas():List<Product>{
        return listOf<Product>(
            Product("margarita",12.0f),
            Product("hawai",11.0f),
            Product("jambon",9f)
        );
    }
}
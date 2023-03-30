package com.example.pizza_app_android

import com.example.pizza_app_android.models.Product

class Datasource {
    fun fetchPizzas():List<Product>{
        return listOf<Product>(
            Product(0,"margarita",12.0f),
            Product(1,"hawai",11.0f),
            Product(2,"jambon",9f)
        );
    }
}
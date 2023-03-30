package com.example.pizza_app_android

import com.example.pizza_app_android.models.Pizza

class Datasource {
    fun fetchPizzas():List<Pizza>{
        return listOf<Pizza>(
            Pizza(0,"margarita",12.0f),
            Pizza(1,"hawai",11.0f),
            Pizza(2,"jambon",9f)
        );
    }
}
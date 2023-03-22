package com.example.pizza_app_android

import com.example.pizza_app_android.models.Pizza

class Datasource {
    fun fetchPizzas():List<Pizza>{
        return listOf<Pizza>(
            Pizza("margarita",12.0f),
            Pizza("hawai",11.0f),
            Pizza("jambon",9f)
        );
    }
}
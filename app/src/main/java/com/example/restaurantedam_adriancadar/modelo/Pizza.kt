package com.example.restaurantedam_adriancadar.modelo

import com.example.restaurantedam_adriancadar.R

data class Pizza(
    var tipo: String = "",
    var tamaño: String = ""
) : Productos("Pizza", R.drawable.pizza)

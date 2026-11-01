package com.example.restaurantedam_adriancadar.modelo

import com.example.restaurantedam_adriancadar.R

data class Hamburguesa(
    var queso: Boolean = false,
    var carne: String = ""
) : Productos("Hamburguesa", R.drawable.hamburguesa)

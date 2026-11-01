package com.example.restaurantedam_adriancadar.ui

import androidx.lifecycle.ViewModel
import com.example.restaurantedam_adriancadar.modelo.Hamburguesa
import com.example.restaurantedam_adriancadar.modelo.Pedidos
import com.example.restaurantedam_adriancadar.modelo.Pizza
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.restaurantedam_adriancadar.modelo.Productos
import kotlinx.coroutines.flow.update

class ViewModelRestaurante : ViewModel() {


    private val _hamburguesaUiState = MutableStateFlow(Hamburguesa())
    val hamburguesaUiState: StateFlow<Hamburguesa> = _hamburguesaUiState.asStateFlow()

    // Pizza
    private val _pizzaUiState = MutableStateFlow(Pizza())
    val pizzaUiState: StateFlow<Pizza> = _pizzaUiState.asStateFlow()

    // Pedidos
    private val _pedidosUiState = MutableStateFlow<List<Pedidos>>(emptyList())
    val pedidosUiState: StateFlow<List<Pedidos>> = _pedidosUiState.asStateFlow()

    // Producto elegido y cantidad
    private val _productoSeleccionado = MutableStateFlow("Hamburguesa")
    val productoSeleccionado: StateFlow<String> = _productoSeleccionado.asStateFlow()

    private val _cantidad = MutableStateFlow("")
    val cantidad: StateFlow<String> = _cantidad.asStateFlow()





    fun seleccionarProducto(nombre: String) {
        _productoSeleccionado.value = nombre
    }

    fun cambiarCantidad(texto: String) {
        if (texto.all { it.isDigit() }) _cantidad.value = texto
    }

    fun cambiarCarne(nueva: String) {
        _hamburguesaUiState.update { it.copy(carne = nueva) }
    }

    fun cambiarQueso(conQueso: Boolean) {
        _hamburguesaUiState.update { it.copy(queso = conQueso) }
    }

    fun cambiarTamano(nuevo: String) {
        _pizzaUiState.update { it.copy(tamaño = nuevo) }
    }

    fun cambiarTipoPizza(nuevo: String) {
        _pizzaUiState.update { it.copy(tipo = nuevo) }
    }
}
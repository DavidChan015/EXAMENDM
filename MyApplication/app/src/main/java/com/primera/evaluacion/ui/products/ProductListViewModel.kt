package com.primera.evaluacion.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.primera.evaluacion.data.repository.ProductRepository
import com.primera.evaluacion.model.ProductListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductListViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _state = MutableStateFlow(ProductListState())
    val state: StateFlow<ProductListState> = _state

    fun cargarProductos() {
        viewModelScope.launch {
            _state.value = _state.value.copy(cargando = true, error = "")

            repository.getProducts()
                .onSuccess { productos ->
                    _state.value = _state.value.copy(
                        productos = productos,
                        cargando = false
                    )
                }
                .onFailure { e ->
                    _state.value = _state.value.copy(
                        cargando = false,
                        error = "Error de conexión: ${e.message}"
                    )
                }
        }
    }
}
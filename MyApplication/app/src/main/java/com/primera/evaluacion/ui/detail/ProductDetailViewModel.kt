package com.primera.evaluacion.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.primera.evaluacion.data.repository.ProductRepository
import com.primera.evaluacion.model.ProductDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _state = MutableStateFlow(ProductDetailState())
    val state: StateFlow<ProductDetailState> = _state

    fun cargarProducto(id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(cargando = true, error = "")

            repository.getProductById(id)
                .onSuccess { producto ->
                    _state.value = _state.value.copy(
                        producto = producto,
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
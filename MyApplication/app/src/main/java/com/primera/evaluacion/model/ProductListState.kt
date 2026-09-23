package com.primera.evaluacion.model

import com.primera.evaluacion.model.response.ProductResponse

data class ProductListState(
    val productos: List<ProductResponse> = emptyList(),
    val cargando: Boolean = false,
    val error: String = ""
)
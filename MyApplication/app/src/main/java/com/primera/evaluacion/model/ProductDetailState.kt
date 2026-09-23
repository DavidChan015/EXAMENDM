package com.primera.evaluacion.model

import com.primera.evaluacion.model.response.ProductResponse

data class ProductDetailState(
    val producto: ProductResponse? = null,
    val cargando: Boolean = false,
    val error: String = ""
)
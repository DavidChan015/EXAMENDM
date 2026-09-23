package com.primera.evaluacion.model


data class LoginState(
    val usuario: String = "",
    val password: String = "",
    val mensaje: String = "",
    val loginExito: Boolean = false
)
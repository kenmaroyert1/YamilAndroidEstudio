package com.example.proyecto1.model

enum class TipoUsuario {
    ESTUDIANTE,
    PROFESOR,
    PERSONAL
}

data class Usuario(
    val id: String,
    val nombre: String,
    val email: String,
    val tipo: TipoUsuario,
    val identificacion: String
)

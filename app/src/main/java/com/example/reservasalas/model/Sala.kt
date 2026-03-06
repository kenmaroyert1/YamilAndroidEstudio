package com.example.reservasalas.model

enum class TipoSala {
    AULA,
    SALA_ESTUDIO,
    LABORATORIO,
    CABINA
}

data class Sala(
    val id: String,
    val nombre: String,
    val tipo: TipoSala,
    val capacidad: Int,
    val edificio: String,
    val piso: Int,
    val equipamiento: List<String> = listOf(),
    val disponible: Boolean = true
) {
    fun descripcionCompleta(): String {
        return "$nombre - $edificio Piso $piso (Capacidad: $capacidad)"
    }
}

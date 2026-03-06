package com.example.parcial

/**
 * Modelos de datos para el sistema UniReservas
 */

data class Reserva(
    val id: Int,
    val sala: String,
    val numeroSala: String,
    val fecha: String,
    val horario: String,
    val usuario: String,
    val tipoUsuario: String,
    val estado: EstadoReserva = EstadoReserva.ACTIVA
) {
    fun toStringResumen(): String {
        return "$sala $numeroSala - $fecha - $horario"
    }
}

enum class EstadoReserva {
    ACTIVA,
    COMPLETADA,
    CANCELADA
}

enum class TipoUsuario {
    ADMIN,
    PROFESOR,
    ESTUDIANTE;
    
    companion object {
        fun fromString(tipo: String): TipoUsuario {
            return when (tipo.uppercase()) {
                "ADMIN", "ADMINISTRADOR" -> ADMIN
                "PROFESOR", "DOCENTE" -> PROFESOR
                else -> ESTUDIANTE
            }
        }
    }
}

package com.example.proyecto1.model

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

enum class EstadoReserva {
    PENDIENTE,
    CONFIRMADA,
    CANCELADA,
    COMPLETADA
}

data class Reserva(
    val id: String,
    val usuario: Usuario,
    val sala: Sala,
    val fecha: LocalDate,
    val horaInicio: LocalTime,
    val horaFin: LocalTime,
    val proposito: String,
    val estado: EstadoReserva = EstadoReserva.PENDIENTE,
    val participantes: Int = 1
) {
    fun formatearFecha(): String {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
    
    fun formatearHora(): String {
        return "${horaInicio.format(DateTimeFormatter.ofPattern("HH:mm"))} - ${horaFin.format(DateTimeFormatter.ofPattern("HH:mm"))}"
    }
    
    fun duracionHoras(): Long {
        return java.time.Duration.between(horaInicio, horaFin).toHours()
    }
}

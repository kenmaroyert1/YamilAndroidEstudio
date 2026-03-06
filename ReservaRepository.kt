package com.example.proyecto1.data

import com.example.proyecto1.model.*
import java.time.LocalDate
import java.time.LocalTime

class ReservaRepository {
    private val _salas = mutableListOf<Sala>()
    private val _reservas = mutableListOf<Reserva>()
    private val _usuarios = mutableListOf<Usuario>()
    
    init {
        cargarDatosDemostracion()
    }
    
    private fun cargarDatosDemostracion() {
        _usuarios.addAll(
            listOf(
                Usuario("1", "Juan Pérez", "juan.perez@universidad.edu", TipoUsuario.ESTUDIANTE, "20201234"),
                Usuario("2", "María García", "maria.garcia@universidad.edu", TipoUsuario.PROFESOR, "P-45678"),
                Usuario("3", "Carlos López", "carlos.lopez@universidad.edu", TipoUsuario.PERSONAL, "A-12345")
            )
        )
        
        _salas.addAll(
            listOf(
                Sala("A101", "Aula 101", TipoSala.AULA, 40, "Edificio A", 1, 
                    listOf("Proyector", "Pizarra digital")),
                Sala("A201", "Aula 201", TipoSala.AULA, 35, "Edificio A", 2, 
                    listOf("Proyector", "Pizarra")),
                Sala("SE01", "Sala Colaborativa 1", TipoSala.SALA_ESTUDIO, 8, "Biblioteca", 2, 
                    listOf("Pizarra", "Mesa grande")),
                Sala("L101", "Lab. Computación 1", TipoSala.LABORATORIO, 30, "Edificio C", 1, 
                    listOf("30 PCs", "Proyector")),
                Sala("C01", "Cabina Reunión 1", TipoSala.CABINA, 4, "Edificio D", 1, 
                    listOf("Videoconferencia", "Pizarra"))
            )
        )
        
        _reservas.addAll(
            listOf(
                Reserva(
                    "R001",
                    _usuarios[0],
                    _salas[2],
                    LocalDate.now(),
                    LocalTime.of(10, 0),
                    LocalTime.of(12, 0),
                    "Estudio en grupo",
                    EstadoReserva.CONFIRMADA,
                    5
                )
            )
        )
    }
    
    fun obtenerSalas(): List<Sala> = _salas.toList()
    
    fun obtenerSalasPorTipo(tipo: TipoSala): List<Sala> {
        return _salas.filter { it.tipo == tipo }
    }
    
    fun obtenerSalaPorId(id: String): Sala? {
        return _salas.find { it.id == id }
    }
    
    fun obtenerReservas(): List<Reserva> = _reservas.toList()
    
    fun obtenerReservasPorUsuario(usuarioId: String): List<Reserva> {
        return _reservas.filter { it.usuario.id == usuarioId }
    }
    
    fun obtenerReservasPorFecha(fecha: LocalDate): List<Reserva> {
        return _reservas.filter { it.fecha == fecha }
    }
    
    fun verificarDisponibilidad(
        salaId: String,
        fecha: LocalDate,
        horaInicio: LocalTime,
        horaFin: LocalTime
    ): Boolean {
        val reservasExistentes = _reservas.filter { 
            it.sala.id == salaId && 
            it.fecha == fecha &&
            it.estado != EstadoReserva.CANCELADA
        }
        
        return reservasExistentes.none { reserva ->
            (horaInicio < reserva.horaFin && horaFin > reserva.horaInicio)
        }
    }
    
    fun crearReserva(reserva: Reserva): Boolean {
        if (verificarDisponibilidad(
            reserva.sala.id,
            reserva.fecha,
            reserva.horaInicio,
            reserva.horaFin
        )) {
            _reservas.add(reserva)
            return true
        }
        return false
    }
    
    fun cancelarReserva(reservaId: String): Boolean {
        val reserva = _reservas.find { it.id == reservaId }
        if (reserva != null) {
            val index = _reservas.indexOf(reserva)
            _reservas[index] = reserva.copy(estado = EstadoReserva.CANCELADA)
            return true
        }
        return false
    }
    
    fun obtenerUsuarioActual(): Usuario {
        return _usuarios.first()
    }
}

package com.example.proyecto1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto1.data.ReservaRepository
import com.example.proyecto1.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

data class ReservaUiState(
    val salas: List<Sala> = emptyList(),
    val reservas: List<Reserva> = emptyList(),
    val usuarioActual: Usuario? = null,
    val salasDisponibles: List<Sala> = emptyList(),
    val filtroTipoSala: TipoSala? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ReservaViewModel : ViewModel() {
    private val repository = ReservaRepository()
    
    private val _uiState = MutableStateFlow(ReservaUiState())
    val uiState: StateFlow<ReservaUiState> = _uiState.asStateFlow()
    
    init {
        cargarDatos()
    }
    
    private fun cargarDatos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            val salas = repository.obtenerSalas()
            val reservas = repository.obtenerReservas()
            val usuario = repository.obtenerUsuarioActual()
            
            _uiState.value = _uiState.value.copy(
                salas = salas,
                salasDisponibles = salas,
                reservas = reservas,
                usuarioActual = usuario,
                isLoading = false
            )
        }
    }
    
    fun filtrarSalasPorTipo(tipo: TipoSala?) {
        val salasFiltradas = if (tipo == null) {
            repository.obtenerSalas()
        } else {
            repository.obtenerSalasPorTipo(tipo)
        }
        
        _uiState.value = _uiState.value.copy(
            salasDisponibles = salasFiltradas,
            filtroTipoSala = tipo
        )
    }
    
    fun buscarSalas(query: String) {
        val salas = if (query.isBlank()) {
            repository.obtenerSalas()
        } else {
            repository.obtenerSalas().filter { sala ->
                sala.nombre.contains(query, ignoreCase = true) ||
                sala.edificio.contains(query, ignoreCase = true)
            }
        }
        
        _uiState.value = _uiState.value.copy(salasDisponibles = salas)
    }
    
    fun verificarDisponibilidad(
        salaId: String,
        fecha: LocalDate,
        horaInicio: LocalTime,
        horaFin: LocalTime
    ): Boolean {
        return repository.verificarDisponibilidad(salaId, fecha, horaInicio, horaFin)
    }
    
    fun crearReserva(
        sala: Sala,
        fecha: LocalDate,
        horaInicio: LocalTime,
        horaFin: LocalTime,
        proposito: String,
        participantes: Int
    ): Boolean {
        val usuario = _uiState.value.usuarioActual ?: return false
        
        val reserva = Reserva(
            id = "R${UUID.randomUUID().toString().substring(0, 8)}",
            usuario = usuario,
            sala = sala,
            fecha = fecha,
            horaInicio = horaInicio,
            horaFin = horaFin,
            proposito = proposito,
            estado = EstadoReserva.CONFIRMADA,
            participantes = participantes
        )
        
        val exito = repository.crearReserva(reserva)
        if (exito) {
            cargarDatos()
        }
        return exito
    }
    
    fun cancelarReserva(reservaId: String): Boolean {
        val exito = repository.cancelarReserva(reservaId)
        if (exito) {
            cargarDatos()
        }
        return exito
    }
    
    fun obtenerMisReservas(): List<Reserva> {
        val usuario = _uiState.value.usuarioActual ?: return emptyList()
        return repository.obtenerReservasPorUsuario(usuario.id)
    }
    
    fun obtenerReservasPorFecha(fecha: LocalDate): List<Reserva> {
        return repository.obtenerReservasPorFecha(fecha)
    }
}

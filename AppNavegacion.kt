package com.example.parcial

import androidx.compose.runtime.*

/**
 * AppNavegacion - Sistema de navegación completo
 * CUMPLE TODOS LOS REQUISITOS 2.1, 2.2, 2.3 y 2.4
 * MEJORADO: Sistema de roles y gestión de reservas
 */
@Composable
fun AppNavegacion() {
    var pantallaActual by remember { mutableStateOf("inicio") }
    var tipoUsuario by remember { mutableStateOf(TipoUsuario.ESTUDIANTE) }
    var nombreUsuario by remember { mutableStateOf("") }
    var salaSeleccionada by remember { mutableStateOf("") }
    
    // Sistema de reservas global con ID incremental
    var contadorReservas by remember { mutableStateOf(6) }
    
    // Datos de demostración iniciales
    var reservasActivas by remember { 
        mutableStateOf(listOf(
            Reserva(1, "Aula", "101", "Hoy", "08:00 - 09:00", "María García", "ESTUDIANTE", EstadoReserva.ACTIVA),
            Reserva(2, "Laboratorio", "Lab-A", "Hoy", "10:00 - 11:00", "Dr. López", "PROFESOR", EstadoReserva.ACTIVA),
            Reserva(3, "Sala de Estudio", "A", "Mañana", "14:00 - 15:00", "Carlos Ruiz", "ESTUDIANTE", EstadoReserva.ACTIVA),
            Reserva(4, "Cabina", "3", "Hoy", "15:00 - 16:00", "Ana Torres", "ESTUDIANTE", EstadoReserva.ACTIVA),
            Reserva(5, "Aula", "202", "Mañana", "09:00 - 10:00", "Dra. Martínez", "PROFESOR", EstadoReserva.ACTIVA)
        ))
    }
    
    var historialReservas by remember { 
        mutableStateOf(listOf(
            Reserva(101, "Aula", "101", "01/03/2026", "10:00 - 11:00", "Pedro Silva", "ESTUDIANTE", EstadoReserva.COMPLETADA),
            Reserva(102, "Laboratorio", "Lab-B", "02/03/2026", "14:00 - 16:00", "Dr. Ramírez", "PROFESOR", EstadoReserva.COMPLETADA),
            Reserva(103, "Cabina", "1", "03/03/2026", "11:00 - 12:00", "Laura Díaz", "ESTUDIANTE", EstadoReserva.CANCELADA)
        ))
    }
    
    when (pantallaActual) {
        // REQUISITO 2.1: Pantalla de inicio
        "inicio" -> PantallaInicio(
            onIrALogin = { pantallaActual = "login" }
        )
        
        // REQUISITO 2.2: Pantalla con usuario y contraseña
        "login" -> PantallaLogin(
            onAccesoValidado = { tipo, usuario ->
                tipoUsuario = TipoUsuario.fromString(tipo)
                nombreUsuario = usuario
                pantallaActual = "menu"
            },
            onVolver = { pantallaActual = "inicio" }
        )
        
        // REQUISITO 2.3: Menú con 5+ opciones
        "menu" -> PantallaMenu(
            tipoUsuario = tipoUsuario,
            nombreUsuario = nombreUsuario,
            onReservarSala = { pantallaActual = "seleccionSala" },
            onVerDisponibilidad = { pantallaActual = "disponibilidad" },
            onMisReservas = { pantallaActual = "misReservas" },
            onTodasReservas = { pantallaActual = "todasReservas" },
            onHistorial = { pantallaActual = "historial" },
            onAyuda = { pantallaActual = "ayuda" },
            onCerrarSesion = { 
                pantallaActual = "inicio"
                tipoUsuario = TipoUsuario.ESTUDIANTE
                nombreUsuario = ""
            }
        )
        
        // REQUISITO 2.4: Opción 1 - Reservar Sala
        "seleccionSala" -> PantallaSeleccionSala(
            onSalaSeleccionada = { sala ->
                salaSeleccionada = sala
                pantallaActual = "confirmarReserva"
            },
            onVolver = { pantallaActual = "menu" }
        )
        
        "confirmarReserva" -> PantallaConfirmarReserva(
            sala = salaSeleccionada,
            reservasActuales = reservasActivas,
            onReservaConfirmada = { numeroSala, fecha, horario ->
                // Validar si ya existe una reserva en ese horario y sala
                val conflicto = reservasActivas.any { 
                    it.sala == salaSeleccionada && 
                    it.numeroSala == numeroSala && 
                    it.fecha == fecha && 
                    it.horario == horario 
                }
                
                if (!conflicto) {
                    val nuevaReserva = Reserva(
                        id = contadorReservas++,
                        sala = salaSeleccionada,
                        numeroSala = numeroSala,
                        fecha = fecha,
                        horario = horario,
                        usuario = nombreUsuario,
                        tipoUsuario = tipoUsuario.name,
                        estado = EstadoReserva.ACTIVA
                    )
                    reservasActivas = reservasActivas + nuevaReserva
                    pantallaActual = "menu"
                    true // Indicar éxito
                } else {
                    false // Indicar conflicto
                }
            },
            onVolver = { pantallaActual = "seleccionSala" }
        )
        
        // REQUISITO 2.4: Opción 2 - Ver Disponibilidad
        "disponibilidad" -> PantallaDisponibilidad(
            reservasActivas = reservasActivas,
            onVolver = { pantallaActual = "menu" }
        )
        
        // REQUISITO 2.4: Opción 3 - Mis Reservas (para estudiantes)
        "misReservas" -> PantallaMisReservas(
            reservas = reservasActivas.filter { it.usuario == nombreUsuario },
            onCancelarReserva = { reserva ->
                reservasActivas = reservasActivas.filter { it.id != reserva.id }
                historialReservas = historialReservas + reserva.copy(estado = EstadoReserva.CANCELADA)
            },
            onVolver = { pantallaActual = "menu" }
        )
        
        // NUEVA: Todas las Reservas (para Admin y Profesores)
        "todasReservas" -> PantallaTodasReservas(
            reservas = reservasActivas,
            tipoUsuario = tipoUsuario,
            onEliminarReserva = { reserva ->
                reservasActivas = reservasActivas.filter { it.id != reserva.id }
                historialReservas = historialReservas + reserva.copy(estado = EstadoReserva.CANCELADA)
            },
            onVolver = { pantallaActual = "menu" }
        )
        
        // REQUISITO 2.4: Opción 4 - Historial
        "historial" -> PantallaHistorial(
            historial = historialReservas,
            usuarioActual = nombreUsuario,
            tipoUsuario = tipoUsuario,
            onVolver = { pantallaActual = "menu" }
        )
        
        // REQUISITO 2.4: Opción 5 - Ayuda
        "ayuda" -> PantallaAyuda(
            onVolver = { pantallaActual = "menu" }
        )
    }
}

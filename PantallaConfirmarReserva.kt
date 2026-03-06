package com.example.parcial

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * REQUISITO 2.4: Pantalla para confirmar reserva con horario
 * MEJORADO: Validación de conflictos de reservas
 */
@Composable
fun PantallaConfirmarReserva(
    sala: String,
    reservasActuales: List<Reserva>,
    onReservaConfirmada: (String, String, String) -> Boolean,
    onVolver: () -> Unit
) {
    var numeroSala by remember { mutableStateOf("") }
    var horarioSeleccionado by remember { mutableStateOf("") }
    var fechaSeleccionada by remember { mutableStateOf("Hoy") }
    var mostrarError by remember { mutableStateOf(false) }
    var mostrarConfirmacion by remember { mutableStateOf(false) }
    var mostrarConflicto by remember { mutableStateOf(false) }
    
    val horarios = listOf(
        "08:00 - 09:00", "09:00 - 10:00", "10:00 - 11:00",
        "11:00 - 12:00", "12:00 - 13:00", "14:00 - 15:00",
        "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00"
    )
    
    // Verificar si un horario está ocupado
    fun estaOcupado(horario: String): Boolean {
        return numeroSala.isNotBlank() && reservasActuales.any { 
            it.sala == sala && 
            it.numeroSala == numeroSala && 
            it.fecha == fechaSeleccionada && 
            it.horario == horario 
        }
    }
    
    // Diálogo de conflicto
    if (mostrarConflicto) {
        AlertDialog(
            onDismissRequest = { mostrarConflicto = false },
            icon = { Text("⚠️", fontSize = 40.sp) },
            title = { Text("Conflicto de Reserva") },
            text = { 
                Text("Lo sentimos, esta sala ya está reservada para el horario seleccionado.\n\nPor favor elige otro horario o sala.") 
            },
            confirmButton = {
                Button(onClick = { mostrarConflicto = false }) {
                    Text("Entendido")
                }
            }
        )
    }
    
    // Diálogo de confirmación
    if (mostrarConfirmacion) {
        AlertDialog(
            onDismissRequest = { mostrarConfirmacion = false },
            icon = { Text("✅", fontSize = 40.sp) },
            title = { Text("¡Reserva Confirmada!") },
            text = { 
                Text("Tu reserva ha sido registrada exitosamente:\n\n$sala $numeroSala\n$fechaSeleccionada\n$horarioSeleccionado") 
            },
            confirmButton = {
                Button(onClick = {
                    mostrarConfirmacion = false
                    val exito = onReservaConfirmada(numeroSala, fechaSeleccionada, horarioSeleccionado)
                    if (!exito) {
                        mostrarConflicto = true
                    }
                }) {
                    Text("Entendido")
                }
            }
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("📅", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Reservar $sala", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        
        // Mensaje didáctico
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("💡", fontSize = 24.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Completa todos los datos para realizar tu reserva",
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Número de sala
        Text(
            text = "Paso 1: Ingresa el número de la sala",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = numeroSala,
            onValueChange = { 
                numeroSala = it
                mostrarError = false
            },
            label = { Text("Número de $sala") },
            placeholder = { Text("Ej: 101, 202, Lab-A") },
            modifier = Modifier.fillMaxWidth(),
            isError = mostrarError && numeroSala.isBlank(),
            supportingText = {
                if (mostrarError && numeroSala.isBlank()) {
                    Text("⚠️ Este campo es obligatorio")
                }
            }
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Fecha
        Text(
            text = "Paso 2: Selecciona la fecha",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { 
                    fechaSeleccionada = "Hoy"
                    mostrarError = false
                },
                modifier = Modifier.weight(1f),
                colors = if (fechaSeleccionada == "Hoy") 
                    ButtonDefaults.buttonColors() 
                else 
                    ButtonDefaults.outlinedButtonColors()
            ) { Text("Hoy") }
            
            Button(
                onClick = { 
                    fechaSeleccionada = "Mañana"
                    mostrarError = false
                },
                modifier = Modifier.weight(1f),
                colors = if (fechaSeleccionada == "Mañana") 
                    ButtonDefaults.buttonColors() 
                else 
                    ButtonDefaults.outlinedButtonColors()
            ) { Text("Mañana") }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Horarios
        Text(
            text = "Paso 3: Elige el horario disponible",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        
        // Leyenda de disponibilidad
        if (numeroSala.isNotBlank()) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("✅", fontSize = 12.sp)
                Text(" = Disponible  ", fontSize = 11.sp, color = MaterialTheme.colorScheme.secondary)
                Text("🔒", fontSize = 12.sp)
                Text(" = Ocupado", fontSize = 11.sp, color = MaterialTheme.colorScheme.secondary)
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        horarios.forEach { horario ->
            val ocupado = estaOcupado(horario)
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (numeroSala.isNotBlank()) {
                        Text(
                            text = if (ocupado) "🔒" else "✅",
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(horario, fontSize = 15.sp)
                }
                Button(
                    onClick = { 
                        if (!ocupado) {
                            horarioSeleccionado = horario
                            mostrarError = false
                        } else {
                            mostrarConflicto = true
                        }
                    },
                    enabled = !ocupado || horarioSeleccionado == horario,
                    colors = if (horarioSeleccionado == horario) 
                        ButtonDefaults.buttonColors() 
                    else if (ocupado)
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    else 
                        ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(
                        if (horarioSeleccionado == horario) "✓ Seleccionado" 
                        else if (ocupado) "Ocupado" 
                        else "Seleccionar"
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        
        // Mensaje de error
        if (mostrarError) {
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("⚠️", fontSize = 24.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Por favor completa todos los campos antes de continuar",
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = {
                if (numeroSala.isNotBlank() && horarioSeleccionado.isNotEmpty()) {
                    mostrarConfirmacion = true
                } else {
                    mostrarError = true
                }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            enabled = true
        ) {
            Text("✅ Confirmar Reserva", fontSize = 18.sp)
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        OutlinedButton(
            onClick = onVolver, 
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("←", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Volver a Selección", fontSize = 16.sp)
            }
        }
    }
}

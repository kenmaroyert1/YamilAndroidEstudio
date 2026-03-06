package com.example.reservasalas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reservasalas.model.Sala
import com.example.reservasalas.viewmodel.ReservaViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservaScreen(
    sala: Sala,
    onNavigateBack: () -> Unit,
    onReservaCreada: () -> Unit,
    viewModel: ReservaViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    var fechaSeleccionada by remember { mutableStateOf(LocalDate.now()) }
    var horaInicio by remember { mutableStateOf(8) }
    var minutoInicio by remember { mutableStateOf(0) }
    var horaFin by remember { mutableStateOf(10) }
    var minutoFin by remember { mutableStateOf(0) }
    var proposito by remember { mutableStateOf("") }
    var participantes by remember { mutableStateOf("1") }
    var showDatePicker by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var showConfirmDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Reserva") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Información de la sala
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = sala.nombre,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = sala.descripcionCompleta(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tipo: ${sala.tipo.name}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    if (sala.equipamiento.isNotEmpty()) {
                        Text(
                            text = "Equipamiento: ${sala.equipamiento.joinToString(", ")}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Selector de fecha
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { showDatePicker = true }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Fecha de reserva", style = MaterialTheme.typography.labelMedium)
                        Text(
                            text = fechaSeleccionada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Icon(Icons.Default.DateRange, "Seleccionar fecha")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Selectores de hora
            Text(
                "Horario",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Hora inicio
                Column(modifier = Modifier.weight(1f)) {
                    Text("Hora Inicio", style = MaterialTheme.typography.labelMedium)
                    
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = horaInicio.toString().padStart(2, '0'),
                            onValueChange = { 
                                it.toIntOrNull()?.let { h -> 
                                    if (h in 0..23) horaInicio = h 
                                }
                            },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            label = { Text("HH") }
                        )
                        
                        OutlinedTextField(
                            value = minutoInicio.toString().padStart(2, '0'),
                            onValueChange = { 
                                it.toIntOrNull()?.let { m -> 
                                    if (m in 0..59) minutoInicio = m 
                                }
                            },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            label = { Text("MM") }
                        )
                    }
                }

                // Hora fin
                Column(modifier = Modifier.weight(1f)) {
                    Text("Hora Fin", style = MaterialTheme.typography.labelMedium)
                    
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = horaFin.toString().padStart(2, '0'),
                            onValueChange = { 
                                it.toIntOrNull()?.let { h -> 
                                    if (h in 0..23) horaFin = h 
                                }
                            },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            label = { Text("HH") }
                        )
                        
                        OutlinedTextField(
                            value = minutoFin.toString().padStart(2, '0'),
                            onValueChange = { 
                                it.toIntOrNull()?.let { m -> 
                                    if (m in 0..59) minutoFin = m 
                                }
                            },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            label = { Text("MM") }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Propósito
            OutlinedTextField(
                value = proposito,
                onValueChange = { proposito = it },
                label = { Text("Propósito de la reserva") },
                placeholder = { Text("Ej: Reunión de grupo, Clase, Estudio...") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Número de participantes
            OutlinedTextField(
                value = participantes,
                onValueChange = { 
                    if (it.all { char -> char.isDigit() }) {
                        participantes = it
                    }
                },
                label = { Text("Número de participantes") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = { Icon(Icons.Default.Person, null) }
            )

            // Mensaje de error
            errorMessage?.let { error ->
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = error,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de reservar
            Button(
                onClick = {
                    val numParticipantes = participantes.toIntOrNull() ?: 0
                    
                    when {
                        proposito.isBlank() -> {
                            errorMessage = "Por favor indica el propósito de la reserva"
                        }
                        numParticipantes <= 0 -> {
                            errorMessage = "Indica un número válido de participantes"
                        }
                        numParticipantes > sala.capacidad -> {
                            errorMessage = "La sala solo tiene capacidad para ${sala.capacidad} personas"
                        }
                        else -> {
                            val inicio = LocalTime.of(horaInicio, minutoInicio)
                            val fin = LocalTime.of(horaFin, minutoFin)
                            
                            if (fin <= inicio) {
                                errorMessage = "La hora de fin debe ser posterior a la hora de inicio"
                            } else if (viewModel.verificarDisponibilidad(
                                sala.id, fechaSeleccionada, inicio, fin
                            )) {
                                showConfirmDialog = true
                            } else {
                                errorMessage = "La sala no está disponible en ese horario"
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Icon(Icons.Default.Check, null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Confirmar Reserva")
            }
        }
    }

    // Diálogo de confirmación
    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            icon = { Icon(Icons.Default.CheckCircle, null) },
            title = { Text("Confirmar Reserva") },
            text = {
                Column {
                    Text("¿Confirmas la reserva con los siguientes datos?")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Sala: ${sala.nombre}", style = MaterialTheme.typography.bodyMedium)
                    Text("• Fecha: ${fechaSeleccionada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}")
                    Text("• Horario: ${LocalTime.of(horaInicio, minutoInicio).format(DateTimeFormatter.ofPattern("HH:mm"))} - ${LocalTime.of(horaFin, minutoFin).format(DateTimeFormatter.ofPattern("HH:mm"))}")
                    Text("• Participantes: $participantes")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val exito = viewModel.crearReserva(
                            sala = sala,
                            fecha = fechaSeleccionada,
                            horaInicio = LocalTime.of(horaInicio, minutoInicio),
                            horaFin = LocalTime.of(horaFin, minutoFin),
                            proposito = proposito,
                            participantes = participantes.toInt()
                        )
                        
                        if (exito) {
                            showConfirmDialog = false
                            onReservaCreada()
                        } else {
                            showConfirmDialog = false
                            errorMessage = "Error al crear la reserva"
                        }
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

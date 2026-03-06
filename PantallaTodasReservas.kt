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
 * Pantalla para Admin y Profesores - Ver/Gestionar todas las reservas
 */
@Composable
fun PantallaTodasReservas(
    reservas: List<Reserva>,
    tipoUsuario: TipoUsuario,
    onEliminarReserva: (Reserva) -> Unit,
    onVolver: () -> Unit
) {
    var mostrarDialogoEliminar by remember { mutableStateOf<Reserva?>(null) }
    
    // Diálogo de confirmación de eliminación (solo Admin)
    mostrarDialogoEliminar?.let { reserva ->
        AlertDialog(
            onDismissRequest = { mostrarDialogoEliminar = null },
            icon = { Text("🔑", fontSize = 40.sp) },
            title = { Text("Eliminar Reserva") },
            text = { 
                Text("¿Estás seguro de eliminar esta reserva?\n\n${reserva.toStringResumen()}\nUsuario: ${reserva.usuario}") 
            },
            confirmButton = {
                Button(
                    onClick = {
                        onEliminarReserva(reserva)
                        mostrarDialogoEliminar = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Sí, eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoEliminar = null }) {
                    Text("Cancelar")
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
        Text(
            text = if (tipoUsuario == TipoUsuario.ADMIN) "🔑" else "👨‍🏫",
            fontSize = 64.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = if (tipoUsuario == TipoUsuario.ADMIN) "Gestionar Reservas" else "Todas las Reservas",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        
        // Mensaje informativo
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (tipoUsuario == TipoUsuario.ADMIN)
                    MaterialTheme.colorScheme.errorContainer
                else
                    MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("💡", fontSize = 28.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = if (tipoUsuario == TipoUsuario.ADMIN)
                        "Como administrador puedes ver y eliminar cualquier reserva del sistema"
                    else
                        "Aquí puedes ver todas las reservas activas del sistema",
                    fontSize = 14.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Contador de reservas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("📊", fontSize = 32.sp)
                    Text("${reservas.size}", fontSize = 28.sp)
                    Text("Total", fontSize = 14.sp)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("🎓", fontSize = 32.sp)
                    Text("${reservas.count { it.tipoUsuario == "ESTUDIANTE" }}", fontSize = 28.sp)
                    Text("Estudiantes", fontSize = 14.sp)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("👨‍🏫", fontSize = 32.sp)
                    Text("${reservas.count { it.tipoUsuario == "PROFESOR" }}", fontSize = 28.sp)
                    Text("Profesores", fontSize = 14.sp)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        if (reservas.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("📭", fontSize = 56.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No hay reservas activas en el sistema",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        } else {
            Text(
                text = "Lista de Reservas:",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            reservas.forEach { reserva ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = when (reserva.tipoUsuario) {
                            "ADMIN" -> MaterialTheme.colorScheme.errorContainer
                            "PROFESOR" -> MaterialTheme.colorScheme.primaryContainer
                            else -> MaterialTheme.colorScheme.secondaryContainer
                        }
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = when (reserva.tipoUsuario) {
                                    "ADMIN" -> "🔑"
                                    "PROFESOR" -> "👨‍🏫"
                                    else -> "🎓"
                                },
                                fontSize = 28.sp
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = reserva.toStringResumen(),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Usuario: ${reserva.usuario}",
                                    fontSize = 14.sp,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "ID: #${reserva.id}",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                        
                        // Solo admin puede eliminar
                        if (tipoUsuario == TipoUsuario.ADMIN) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = { mostrarDialogoEliminar = reserva },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.error
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("🗑️ Eliminar Reserva")
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        OutlinedButton(
            onClick = onVolver, 
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("←", fontSize = 24.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Text("Volver al Menú", fontSize = 18.sp)
            }
        }
    }
}

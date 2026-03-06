package com.example.parcial

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * REQUISITO 2.4: Pantalla Mis Reservas
 */
@Composable
fun PantallaMisReservas(
    reservas: List<Reserva>,
    onCancelarReserva: (Reserva) -> Unit,
    onVolver: () -> Unit
) {
    var mostrarDialogoCancelar by remember { mutableStateOf<Reserva?>(null) }
    
    // Diálogo de confirmación de cancelación
    mostrarDialogoCancelar?.let { reserva ->
        AlertDialog(
            onDismissRequest = { mostrarDialogoCancelar = null },
            icon = { Text("⚠️", fontSize = 40.sp) },
            title = { Text("¿Cancelar Reserva?") },
            text = { 
                Text("¿Estás seguro de que deseas cancelar esta reserva?\n\n${reserva.toStringResumen()}") 
            },
            confirmButton = {
                Button(
                    onClick = {
                        onCancelarReserva(reserva)
                        mostrarDialogoCancelar = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Sí, cancelar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoCancelar = null }) {
                    Text("No, mantener")
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
        Text("📋", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Mis Reservas Activas", style = MaterialTheme.typography.headlineMedium)
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
                    if (reservas.isEmpty()) 
                        "Aquí aparecerán tus reservas activas"
                    else 
                        "Puedes cancelar una reserva si ya no la necesitas",
                    fontSize = 13.sp
                )
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
                    modifier = Modifier.fillMaxWidth().padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("📭", fontSize = 48.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("No tienes reservas activas")
                }
            }
        } else {
            reservas.forEach { reserva ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = reserva.toStringResumen(),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Reserva #${reserva.id}",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { mostrarDialogoCancelar = reserva },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("❌ Cancelar Reserva")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
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
                Text("Volver al Menú", fontSize = 16.sp)
            }
        }
    }
}

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
 * REQUISITO 2.4: Pantalla Historial
 * MEJORADO: Con búsqueda, filtros y estadísticas
 */
@Composable
fun PantallaHistorial(
    historial: List<Reserva>,
    usuarioActual: String,
    tipoUsuario: TipoUsuario,
    onVolver: () -> Unit
) {
    var textoBusqueda by remember { mutableStateOf("") }
    var filtroEstado by remember { mutableStateOf("TODOS") }
    
    // Filtrar historial según el tipo de usuario
    val historialBase = if (tipoUsuario == TipoUsuario.ESTUDIANTE) {
        historial.filter { it.usuario == usuarioActual }
    } else {
        historial
    }
    
    // Aplicar búsqueda y filtros
    val historialFiltrado = remember(historialBase, textoBusqueda, filtroEstado) {
        historialBase.filter { reserva ->
            val coincideBusqueda = textoBusqueda.isBlank() || 
                reserva.sala.contains(textoBusqueda, ignoreCase = true) ||
                reserva.numeroSala.contains(textoBusqueda, ignoreCase = true) ||
                reserva.usuario.contains(textoBusqueda, ignoreCase = true) ||
                reserva.fecha.contains(textoBusqueda, ignoreCase = true)
            
            val coincideEstado = when (filtroEstado) {
                "COMPLETADA" -> reserva.estado == EstadoReserva.COMPLETADA
                "CANCELADA" -> reserva.estado == EstadoReserva.CANCELADA
                else -> true
            }
            
            coincideBusqueda && coincideEstado
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("📜", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Historial de Reservas", style = MaterialTheme.typography.headlineMedium)
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
                    text = if (tipoUsuario == TipoUsuario.ESTUDIANTE)
                        "Aquí puedes ver tus reservas pasadas y canceladas"
                    else
                        "Historial completo de reservas ${if (tipoUsuario == TipoUsuario.ADMIN) "del sistema" else ""}",
                    fontSize = 13.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Campo de búsqueda
        OutlinedTextField(
            value = textoBusqueda,
            onValueChange = { textoBusqueda = it },
            label = { Text("Buscar en historial") },
            placeholder = { Text("Buscar por sala, usuario, fecha...") },
            leadingIcon = { Text("🔍") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            trailingIcon = {
                if (textoBusqueda.isNotEmpty()) {
                    IconButton(onClick = { textoBusqueda = "" }) {
                        Text("✕", fontSize = 16.sp)
                    }
                }
            }
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Filtros por estado
        Text(
            text = "Filtrar por estado:",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = filtroEstado == "TODOS",
                onClick = { filtroEstado = "TODOS" },
                label = { Text("Todos") }
            )
            FilterChip(
                selected = filtroEstado == "COMPLETADA",
                onClick = { filtroEstado = "COMPLETADA" },
                label = { Text("✓ Completadas") }
            )
            FilterChip(
                selected = filtroEstado == "CANCELADA",
                onClick = { filtroEstado = "CANCELADA" },
                label = { Text("✗ Canceladas") }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Estadísticas del historial
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
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("📊", fontSize = 24.sp)
                    Text("${historialFiltrado.size}", fontSize = 20.sp, style = MaterialTheme.typography.titleLarge)
                    Text("Total", fontSize = 11.sp)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("✓", fontSize = 24.sp)
                    Text("${historialBase.count { it.estado == EstadoReserva.COMPLETADA }}", fontSize = 20.sp, style = MaterialTheme.typography.titleLarge)
                    Text("Completadas", fontSize = 11.sp)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("✗", fontSize = 24.sp)
                    Text("${historialBase.count { it.estado == EstadoReserva.CANCELADA }}", fontSize = 20.sp, style = MaterialTheme.typography.titleLarge)
                    Text("Canceladas", fontSize = 11.sp)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        if (historialFiltrado.isEmpty()) {
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
                    Text(
                        text = if (textoBusqueda.isNotEmpty() || filtroEstado != "TODOS")
                            "No se encontraron reservas con estos filtros"
                        else
                            "No hay reservas en el historial"
                    )
                }
            }
        } else {
            historialFiltrado.forEach { reserva ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (reserva.estado == EstadoReserva.CANCELADA) 
                            MaterialTheme.colorScheme.errorContainer 
                        else 
                            MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = reserva.toStringResumen(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            
                            // Mostrar usuario si es Admin o Profesor
                            if (tipoUsuario != TipoUsuario.ESTUDIANTE) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = when (reserva.tipoUsuario) {
                                            "ADMIN" -> "🔑"
                                            "PROFESOR" -> "👨‍🏫"
                                            else -> "🎓"
                                        },
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = reserva.usuario,
                                        fontSize = 12.sp
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                            
                            Text(
                                text = "Estado: ${when (reserva.estado) {
                                    EstadoReserva.COMPLETADA -> "Completada"
                                    EstadoReserva.CANCELADA -> "Cancelada"
                                    else -> "Activa"
                                }}",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                        Text(
                            text = if (reserva.estado == EstadoReserva.CANCELADA) "✗" else "✓",
                            fontSize = 24.sp,
                            color = if (reserva.estado == EstadoReserva.CANCELADA) 
                                MaterialTheme.colorScheme.error 
                            else 
                                MaterialTheme.colorScheme.primary
                        )
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

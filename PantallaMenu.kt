package com.example.parcial

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * REQUISITO 2.3: Pantalla3 - Menú de opciones (Barra de navegación)
 * MÍNIMO 5 OPCIONES que corresponden a la funcionalidad de reserva de salas
 * MEJORADO: Opciones según rol de usuario
 */
@Composable
fun PantallaMenu(
    tipoUsuario: TipoUsuario,
    nombreUsuario: String,
    onReservarSala: () -> Unit,
    onVerDisponibilidad: () -> Unit,
    onMisReservas: () -> Unit,
    onTodasReservas: () -> Unit,
    onHistorial: () -> Unit,
    onAyuda: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    var mostrarDialogoCerrarSesion by remember { mutableStateOf(false) }
    
    // Diálogo de confirmación para cerrar sesión
    if (mostrarDialogoCerrarSesion) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoCerrarSesion = false },
            icon = { Text("🚪", fontSize = 40.sp) },
            title = { Text("¿Cerrar Sesión?") },
            text = { 
                Column {
                    Text("¿Deseas cerrar tu sesión actual?\n")
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("Sesión Actual:", style = MaterialTheme.typography.titleSmall)
                            Text("Usuario: $nombreUsuario", fontSize = 13.sp)
                            Text("Rol: ${when (tipoUsuario) {
                                TipoUsuario.ADMIN -> "🔑 Administrador"
                                TipoUsuario.PROFESOR -> "👨‍🏫 Profesor"
                                TipoUsuario.ESTUDIANTE -> "🎓 Estudiante"
                            }}", fontSize = 13.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Regresarás al inicio y podrás acceder con otro usuario.", 
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        mostrarDialogoCerrarSesion = false
                        onCerrarSesion()
                    }
                ) {
                    Text("Sí, cerrar sesión")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoCerrarSesion = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🏢", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "UniReservas",
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Card(colors = CardDefaults.cardColors(
            containerColor = when (tipoUsuario) {
                TipoUsuario.ADMIN -> MaterialTheme.colorScheme.errorContainer
                TipoUsuario.PROFESOR -> MaterialTheme.colorScheme.primaryContainer
                TipoUsuario.ESTUDIANTE -> MaterialTheme.colorScheme.secondaryContainer
            }
        )) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = when (tipoUsuario) {
                        TipoUsuario.ADMIN -> "🔑 ADMINISTRADOR"
                        TipoUsuario.PROFESOR -> "👨‍🏫 PROFESOR"
                        TipoUsuario.ESTUDIANTE -> "🎓 ESTUDIANTE"
                    },
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = nombreUsuario,
                    fontSize = 12.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "MENÚ PRINCIPAL",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleMedium
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // OPCIÓN 1: Reservar Sala (Requisito 2.3)
        Button(
            onClick = onReservarSala,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("🎯 Reservar Sala", fontSize = 18.sp)
                Text("→")
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // OPCIÓN 2: Ver Disponibilidad (Requisito 2.3)
        Button(
            onClick = onVerDisponibilidad,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("📊 Ver Disponibilidad", fontSize = 18.sp)
                Text("→")
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // OPCIÓN 3: Mis Reservas (Estudiantes) / Todas las Reservas (Admin/Profesor)
        if (tipoUsuario == TipoUsuario.ESTUDIANTE) {
            Button(
                onClick = onMisReservas,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("📋 Mis Reservas", fontSize = 18.sp)
                    Text("→")
                }
            }
        } else {
            Button(
                onClick = onTodasReservas,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = if (tipoUsuario == TipoUsuario.ADMIN)
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                else
                    ButtonDefaults.buttonColors()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "${if (tipoUsuario == TipoUsuario.ADMIN) "🔑" else "👨‍🏫"} ${if (tipoUsuario == TipoUsuario.ADMIN) "Gestionar" else "Ver"} Todas las Reservas",
                        fontSize = 18.sp
                    )
                    Text("→")
                }
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // OPCIÓN 4: Historial (Requisito 2.3)
        Button(
            onClick = onHistorial,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("📜 Historial", fontSize = 18.sp)
                Text("→")
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // OPCIÓN 5: Ayuda (Requisito 2.3)
        Button(
            onClick = onAyuda,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("ℹ️ Ayuda", fontSize = 18.sp)
                Text("→")
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Botón de Cerrar Sesión mejorado
        OutlinedButton(
            onClick = { mostrarDialogoCerrarSesion = true },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.error
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("🚪", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cerrar Sesión", fontSize = 16.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Mensaje informativo
        Text(
            text = "💡 Cierra sesión para cambiar de usuario o rol",
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

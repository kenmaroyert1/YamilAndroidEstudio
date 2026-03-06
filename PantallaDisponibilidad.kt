package com.example.parcial

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * REQUISITO 2.4: Pantalla Ver Disponibilidad
 * MEJORADO: Muestra disponibilidad en tiempo real basada en reservas actuales
 */
@Composable
fun PantallaDisponibilidad(
    reservasActivas: List<Reserva>,
    onVolver: () -> Unit
) {
    var fechaSeleccionada by remember { mutableStateOf("Hoy") }
    
    // Función para verificar si una sala está ocupada
    fun estaOcupada(sala: String, numeroSala: String): Boolean {
        return reservasActivas.any { 
            it.sala == sala && 
            it.numeroSala == numeroSala && 
            it.fecha == fechaSeleccionada 
        }
    }
    
    // Salas disponibles por categoría
    val salasAulas = listOf("101", "102", "103", "201", "202", "301")
    val salasSalas = listOf("A", "B", "C", "D")
    val salasLabs = listOf("Lab-A", "Lab-B", "Lab-C")
    val salasCabinas = listOf("1", "2", "3", "4", "5")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("📊", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Disponibilidad de Salas", style = MaterialTheme.typography.headlineMedium)
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
                    "✅ = Disponible  |  ❌ = Ocupada | Datos en tiempo real",
                    fontSize = 13.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Selector de fecha
        Text(
            text = "Selecciona fecha:",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = fechaSeleccionada == "Hoy",
                onClick = { fechaSeleccionada = "Hoy" },
                label = { Text("Hoy") }
            )
            FilterChip(
                selected = fechaSeleccionada == "Mañana",
                onClick = { fechaSeleccionada = "Mañana" },
                label = { Text("Mañana") }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Estadísticas generales
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
                    Text("📋", fontSize = 24.sp)
                    Text(
                        "${reservasActivas.count { it.fecha == fechaSeleccionada }}", 
                        fontSize = 20.sp, 
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text("Reservadas", fontSize = 11.sp)
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
                    Text("✅", fontSize = 24.sp)
                    val totalSalas = salasAulas.size + salasSalas.size + salasLabs.size + salasCabinas.size
                    val ocupadas = reservasActivas.count { it.fecha == fechaSeleccionada }
                    Text(
                        "${totalSalas - ocupadas}+", 
                        fontSize = 20.sp, 
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text("Disponibles", fontSize = 11.sp)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Aulas
        CardCategoriaDinamica(
            emoji = "🎒",
            nombre = "Aulas",
            tipoSala = "Aula",
            salas = salasAulas,
            estaOcupada = { estaOcupada("Aula", it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        // Salas de Estudio
        CardCategoriaDinamica(
            emoji = "📚",
            nombre = "Salas de Estudio",
            tipoSala = "Sala de Estudio",
            salas = salasSalas,
            estaOcupada = { estaOcupada("Sala de Estudio", it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        // Laboratorios
        CardCategoriaDinamica(
            emoji = "🔬",
            nombre = "Laboratorios",
            tipoSala = "Laboratorio",
            salas = salasLabs,
            estaOcupada = { estaOcupada("Laboratorio", it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        // Cabinas
        CardCategoriaDinamica(
            emoji = "🎧",
            nombre = "Cabinas",
            tipoSala = "Cabina",
            salas = salasCabinas,
            estaOcupada = { estaOcupada("Cabina", it) }
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
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

@Composable
private fun CardCategoriaDinamica(
    emoji: String,
    nombre: String,
    tipoSala: String,
    salas: List<String>,
    estaOcupada: (String) -> Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(emoji, fontSize = 24.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(nombre, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "(${salas.count { !estaOcupada(it) }}/${salas.size} libres)",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            
            salas.forEach { numeroSala ->
                val ocupada = estaOcupada(numeroSala)
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("$tipoSala $numeroSala")
                    Card(colors = CardDefaults.cardColors(
                        containerColor = if (!ocupada) Color(0xFF4CAF50) else Color(0xFFF44336)
                    )) {
                        Text(
                            text = if (!ocupada) "✅ Disponible" else "❌ Ocupada",
                            color = Color.White,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

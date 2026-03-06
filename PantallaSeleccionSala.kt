package com.example.parcial

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * REQUISITO 2.4: Pantalla de selección de tipo de sala
 * Aulas, Salas de Estudio, Laboratorios, Cabinas
 */
@Composable
fun PantallaSeleccionSala(
    onSalaSeleccionada: (String) -> Unit,
    onVolver: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🏢", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Selecciona el Tipo de Sala", style = MaterialTheme.typography.headlineMedium)
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
                    "Elige el tipo de sala que deseas reservar",
                    fontSize = 13.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Aulas
        Button(
            onClick = { onSalaSeleccionada("Aula") },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Text("🎒 Aula", fontSize = 20.sp)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Sala de Estudio
        Button(
            onClick = { onSalaSeleccionada("Sala de Estudio") },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Text("📚 Sala de Estudio", fontSize = 20.sp)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Laboratorio
        Button(
            onClick = { onSalaSeleccionada("Laboratorio") },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Text("🔬 Laboratorio", fontSize = 20.sp)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Cabina
        Button(
            onClick = { onSalaSeleccionada("Cabina") },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Text("🎧 Cabina", fontSize = 20.sp)
        }
        
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

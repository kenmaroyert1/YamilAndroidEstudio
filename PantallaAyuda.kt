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
 * REQUISITO 2.4: Pantalla Ayuda
 */
@Composable
fun PantallaAyuda(onVolver: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("ℹ️", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Centro de Ayuda", style = MaterialTheme.typography.headlineMedium)
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
                    "Encuentra toda la información que necesitas aquí",
                    fontSize = 13.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        CardInfo(
            titulo = "📖 ¿Cómo funciona?",
            contenido = "1. Selecciona el tipo de sala\n2. Elige el horario disponible\n3. Confirma tu reserva\n4. Recibe confirmación"
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        CardInfo(
            titulo = "🏢 Tipos de Salas",
            contenido = "🎒 Aulas: Para clases\n📚 Salas: Estudio grupal\n🔬 Laboratorios: Equipos\n🎧 Cabinas: Estudio individual"
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        CardInfo(
            titulo = "⏰ Horarios",
            contenido = "Lunes a Viernes: 8:00 AM - 6:00 PM\nSábados: 9:00 AM - 2:00 PM\nDomingos: Cerrado"
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        CardInfo(
            titulo = "📋 Políticas",
            contenido = "• Cancela con 2 horas de anticipación\n• Llega 10 minutos antes\n• Respeta el horario\n• Deja el espacio limpio"
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("📞 Contacto", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Email: reservas@universidad.edu")
                Text("Teléfono: (555) 123-4567")
                Text("Horario: Lun-Vie 8AM-6PM")
            }
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

@Composable
private fun CardInfo(titulo: String, contenido: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(titulo, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(contenido)
        }
    }
}

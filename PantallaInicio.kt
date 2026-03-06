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
 * REQUISITO 2.1: Pantalla de inicio
 * - Nombre de la App
 * - Nombres de los miembros del equipo de estudiantes
 * - Botón para navegar a Pantalla2
 * MEJORADO: Agregadas características, versión y descripción detallada
 */
@Composable
fun PantallaInicio(onIrALogin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icono
        Text(
            text = "🏫",
            fontSize = 72.sp
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // NOMBRE DE LA APP (Requisito 2.1)
        Text(
            text = "UniReservas",
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Sistema de Reserva de Salas",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        // Versión
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = "v1.0",
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Características principales
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "✨ Características",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                CaracteristicaItem("📅", "Reserva de Aulas y Salas")
                CaracteristicaItem("🔔", "Sistema de Disponibilidad en Tiempo Real")
                CaracteristicaItem("👥", "Roles: Admin, Profesor y Estudiante")
                CaracteristicaItem("📊", "Historial y Estadísticas")
                CaracteristicaItem("🔍", "Búsqueda y Filtros Avanzados")
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // NOMBRES DEL EQUIPO DE ESTUDIANTES (Requisito 2.1)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "👥 Equipo de Desarrollo",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "👨‍💻 Juan Pérez García", fontSize = 15.sp)
                Text(text = "👩‍💻 María López Rodríguez", fontSize = 15.sp)
                Text(text = "👨‍💻 Carlos Sánchez Torres", fontSize = 15.sp)
                Text(text = "👩‍💻 Ana Martínez Ruiz", fontSize = 15.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // BOTÓN PARA NAVEGAR A PANTALLA2 (Requisito 2.1)
        Button(
            onClick = onIrALogin,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Ingresar al Sistema", fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text("→", fontSize = 20.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Información adicional
        Text(
            text = "Universidad Nacional • Proyecto Final",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun CaracteristicaItem(icono: String, texto: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(icono, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(12.dp))
        Text(texto, fontSize = 14.sp)
    }
}

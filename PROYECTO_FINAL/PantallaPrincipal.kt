package com.example.proyecto1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * PantallaPrincipal - Pantalla principal de la aplicación
 * Muestra las opciones principales con botones
 */
@Composable
fun PantallaPrincipal(onCerrarSesion: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "✅ Pantalla Principal",
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "¡Proyecto funcionando!",
            fontSize = 18.sp
        )
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // Botones de opciones
        Button(
            onClick = { /* Acción 1 */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Opción 1")
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Button(
            onClick = { /* Acción 2 */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Opción 2")
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Button(
            onClick = { /* Acción 3 */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Opción 3")
        }
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // Botón de cerrar sesión
        OutlinedButton(
            onClick = onCerrarSesion,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar Sesión")
        }
    }
}

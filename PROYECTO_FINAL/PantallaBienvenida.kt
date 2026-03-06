package com.example.proyecto1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * PantallaBienvenida - Primera pantalla que ve el usuario
 * Muestra un saludo y los nombres del equipo
 */
@Composable
fun PantallaBienvenida(onIrLogin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título principal
        Text(
            text = "🎓 Bienvenido",
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Proyecto Universitario",
            fontSize = 18.sp
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Nombres del equipo
        Text(text = "Equipo de desarrollo:", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "• Juan Pérez García")
        Text(text = "• María López Rodríguez")
        Text(text = "• Carlos Sánchez Torres")
        Text(text = "• Ana Martínez Ruiz")
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // Botón para ir al login
        Button(
            onClick = onIrLogin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Comenzar")
        }
    }
}

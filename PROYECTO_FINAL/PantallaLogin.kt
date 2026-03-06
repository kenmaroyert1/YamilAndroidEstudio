package com.example.proyecto1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * PantallaLogin - Pantalla de inicio de sesión
 * Permite ingresar email y contraseña
 */
@Composable
fun PantallaLogin(onIrPrincipal: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "🏫 Iniciar Sesión",
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // Campo de email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Campo de contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Botón de entrar
        Button(
            onClick = onIrPrincipal,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }
    }
}

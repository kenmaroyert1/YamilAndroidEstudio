package com.example.reservasalas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reservasalas.model.TipoUsuario

@Composable
fun LoginScreen(
    onLoginSuccess: (String, TipoUsuario) -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var tipoUsuario by remember { mutableStateOf(TipoUsuario.ESTUDIANTE) }
    var expanded by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo y título
        Text(
            text = "🏫",
            fontSize = 64.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Text(
            text = "Sistema de Reserva de Salas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            text = "Universidad",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Campo de email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo institucional") },
            placeholder = { Text("usuario@universidad.edu") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Campo de contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Selector de tipo de usuario
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            OutlinedTextField(
                value = when (tipoUsuario) {
                    TipoUsuario.ESTUDIANTE -> "Estudiante"
                    TipoUsuario.PROFESOR -> "Profesor"
                    TipoUsuario.PERSONAL -> "Personal Administrativo"
                },
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo de usuario") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                TipoUsuario.entries.forEach { tipo ->
                    DropdownMenuItem(
                        text = { 
                            Text(when (tipo) {
                                TipoUsuario.ESTUDIANTE -> "Estudiante"
                                TipoUsuario.PROFESOR -> "Profesor"
                                TipoUsuario.PERSONAL -> "Personal Administrativo"
                            })
                        },
                        onClick = {
                            tipoUsuario = tipo
                            expanded = false
                        }
                    )
                }
            }
        }

        // Mensaje de error
        errorMessage?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Botón de inicio de sesión
        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    errorMessage = "Por favor completa todos los campos"
                } else if (!email.endsWith("@universidad.edu")) {
                    errorMessage = "Usa tu correo institucional"
                } else {
                    // Simulación de autenticación exitosa
                    errorMessage = null
                    onLoginSuccess(email, tipoUsuario)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Iniciar Sesión", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto de ayuda
        TextButton(onClick = { /* Recuperar contraseña */ }) {
            Text("¿Olvidaste tu contraseña?")
        }
    }
}

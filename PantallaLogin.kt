package com.example.parcial

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Validaciones de formulario
 */
private fun validarUsuario(texto: String): String {
    return when {
        texto.isBlank() -> "El usuario es requerido"
        texto.length < 3 -> "Mínimo 3 caracteres"
        texto.length > 20 -> "Máximo 20 caracteres"
        !texto.matches(Regex("^[a-zA-Z0-9_-]+$")) -> "Solo letras, números, guiones y _"
        else -> ""
    }
}

private fun validarContrasena(texto: String): String {
    return when {
        texto.isBlank() -> "La contraseña es requerida"
        texto.length < 4 -> "Mínimo 4 caracteres"
        else -> ""
    }
}

/**
 * REQUISITO 2.2: Pantalla2
 * - Dos campos: Usuario y Contraseña  
 * - Botón para validar el acceso
 * - Navega a Pantalla3 (Menú)
 */
@Composable
fun PantallaLogin(
    onAccesoValidado: (String, String) -> Unit,
    onVolver: () -> Unit = {}
) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var tipoUsuario by remember { mutableStateOf("Estudiante") }
    var errorUsuario by remember { mutableStateOf("") }
    var errorContrasena by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "🔐 Iniciar Sesión",
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
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
                    "Ingresa tus credenciales para acceder al sistema",
                    fontSize = 13.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // CAMPO 1: USUARIO (Requisito 2.2)
        OutlinedTextField(
            value = usuario,
            onValueChange = { 
                usuario = it.trim()  // Eliminar espacios
                errorUsuario = ""
            },
            label = { Text("Usuario") },
            placeholder = { Text("Ingrese su usuario") },
            leadingIcon = { Text("👤") },
            modifier = Modifier.fillMaxWidth(),
            isError = errorUsuario.isNotEmpty(),
            supportingText = {
                if (errorUsuario.isNotEmpty()) {
                    Text(errorUsuario, color = MaterialTheme.colorScheme.error)
                } else {
                    Text("Min. 3 caracteres, solo letras, números y guiones", fontSize = 11.sp)
                }
            },
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // CAMPO 2: CONTRASEÑA (Requisito 2.2)
        OutlinedTextField(
            value = contrasena,
            onValueChange = { 
                contrasena = it
                errorContrasena = ""
            },
            label = { Text("Contraseña") },
            placeholder = { Text("Ingrese su contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = { Text("🔒") },
            modifier = Modifier.fillMaxWidth(),
            isError = errorContrasena.isNotEmpty(),
            supportingText = {
                if (errorContrasena.isNotEmpty()) {
                    Text(errorContrasena, color = MaterialTheme.colorScheme.error)
                } else {
                    Text("Mínimo 4 caracteres", fontSize = 11.sp)
                }
            },
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Selector de tipo de usuario
        Text("Tipo de Usuario:", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilterChip(
                selected = tipoUsuario == "Estudiante",
                onClick = { tipoUsuario = "Estudiante" },
                label = { Text("🎓 Estudiante") }
            )
            FilterChip(
                selected = tipoUsuario == "Profesor",
                onClick = { tipoUsuario = "Profesor" },
                label = { Text("👨‍🏫 Profesor") }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            FilterChip(
                selected = tipoUsuario == "Admin",
                onClick = { tipoUsuario = "Admin" },
                label = { Text("🔑 Admin") }
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Info de roles
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("ℹ️ Roles:", fontSize = 12.sp, style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text("🎓 Estudiante: Ver y gestionar sus reservas", fontSize = 11.sp)
                Text("👨‍🏫 Profesor: Ver todas las reservas", fontSize = 11.sp)
                Text("🔑 Admin: Gestionar todo el sistema", fontSize = 11.sp)
            }
        }
        
        if (errorUsuario.isNotEmpty() || errorContrasena.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("⚠️", fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Complete correctamente todos los campos", fontSize = 13.sp)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // BOTÓN PARA VALIDAR ACCESO Y NAVEGAR A PANTALLA3 (Requisito 2.2)
        Button(
            onClick = {
                // Validar campos
                val errorUser = validarUsuario(usuario)
                val errorPass = validarContrasena(contrasena)
                
                if (errorUser.isEmpty() && errorPass.isEmpty()) {
                    // Acceso validado
                    onAccesoValidado(tipoUsuario, usuario)
                } else {
                    // Mostrar errores
                    errorUsuario = errorUser
                    errorContrasena = errorPass
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Acceder", fontSize = 18.sp)
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
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
                Text("Volver al Inicio", fontSize = 16.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "💡 Demo: Cualquier usuario/contraseña funciona",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

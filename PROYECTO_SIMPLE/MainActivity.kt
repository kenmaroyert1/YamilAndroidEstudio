package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppSimple()
            }
        }
    }
}

@Composable
fun AppSimple() {
    var pantallaActual by remember { mutableStateOf("bienvenida") }
    
    when (pantallaActual) {
        "bienvenida" -> PantallaBienvenida { pantallaActual = "login" }
        "login" -> PantallaLogin { pantallaActual = "principal" }
        "principal" -> PantallaPrincipal { pantallaActual = "bienvenida" }
    }
}

@Composable
fun PantallaBienvenida(onSiguiente: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🎓",
            fontSize = 80.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Bienvenido",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Integrantes:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("• Juan Pérez García", fontSize = 16.sp)
        Text("• María López Rodríguez", fontSize = 16.sp)
        Text("• Carlos Sánchez Torres", fontSize = 16.sp)
        Text("• Ana Martínez Ruiz", fontSize = 16.sp)
        
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = onSiguiente,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Comenzar", fontSize = 18.sp)
        }
    }
}

@Composable
fun PantallaLogin(onLogin: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🏫",
            fontSize = 80.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Iniciar Sesión",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onLogin,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Entrar", fontSize = 18.sp)
        }
    }
}

@Composable
fun PantallaPrincipal(onSalir: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "✅",
            fontSize = 80.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Pantalla Principal",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "¡Proyecto funcionando!",
            fontSize = 18.sp
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = { /* Acción 1 */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Opción 1")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Acción 2 */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Opción 2")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Acción 3 */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Opción 3")
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        OutlinedButton(
            onClick = onSalir,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar Sesión")
        }
    }
}

package com.example.proyecto1

import androidx.compose.runtime.*

/**
 * AppNavegacion - Sistema de navegación SIMPLE sin librerías externas
 * Solo usa un estado para controlar qué pantalla mostrar
 */
@Composable
fun AppNavegacion() {
    // Estado que controla qué pantalla mostrar
    var pantallaActual by remember { mutableStateOf("bienvenida") }
    
    // Simple switch entre pantallas
    when (pantallaActual) {
        "bienvenida" -> PantallaBienvenida(
            onIrLogin = { pantallaActual = "login" }
        )
        "login" -> PantallaLogin(
            onIrPrincipal = { pantallaActual = "principal" }
        )
        "principal" -> PantallaPrincipal(
            onCerrarSesion = { pantallaActual = "bienvenida" }
        )
    }
}

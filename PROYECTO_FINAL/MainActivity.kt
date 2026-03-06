package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

/**
 * MainActivity - Punto de entrada de la aplicación
 * Solo configura el tema y llama a la navegación principal
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    // Llama al sistema de navegación simple
                    AppNavegacion()
                }
            }
        }
    }
}

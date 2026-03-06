package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyecto1.navigation.ReservaApp
import com.example.proyecto1.ui.theme.ReservaSalasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReservaSalasTheme {
                ReservaApp()
            }
        }
    }
}

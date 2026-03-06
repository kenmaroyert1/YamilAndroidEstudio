package com.example.reservasalas.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.reservasalas.model.Sala
import com.example.reservasalas.model.TipoUsuario
import com.example.reservasalas.ui.screens.*
import com.example.reservasalas.viewmodel.ReservaViewModel

// Definición de rutas de navegación
sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Home : Screen("home")
    object Calendario : Screen("calendario")
    object Disponibilidad : Screen("disponibilidad")
    object MisReservas : Screen("mis_reservas")
    object Perfil : Screen("perfil")
    object Reserva : Screen("reserva")
}

// Elementos del menú de navegación inferior
sealed class BottomNavItem(
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val title: String
) {
    object Buscar : BottomNavItem(Screen.Home.route, Icons.Default.Search, "Buscar")
    object Calendario : BottomNavItem(Screen.Calendario.route, Icons.Default.CalendarToday, "Calendario")
    object Disponibilidad : BottomNavItem(Screen.Disponibilidad.route, Icons.Default.EventAvailable, "Disponibilidad")
    object MisReservas : BottomNavItem(Screen.MisReservas.route, Icons.Default.BookOnline, "Mis Reservas")
    object Perfil : BottomNavItem(Screen.Perfil.route, Icons.Default.Person, "Perfil")
}

@Composable
fun ReservaApp(
    navController: NavHostController = rememberNavController(),
    viewModel: ReservaViewModel = viewModel()
) {
    var isUserLoggedIn by remember { mutableStateOf(false) }
    var userEmail by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf(TipoUsuario.ESTUDIANTE) }
    var selectedSala by remember { mutableStateOf<Sala?>(null) }

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        // Pantalla 1: Bienvenida con nombres del equipo
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        // Pantalla 2: Login (Usuario y contraseña)
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { email, tipo ->
                    userEmail = email
                    userType = tipo
                    isUserLoggedIn = true
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla 3: Home con menú de navegación (5 opciones)
        composable(Screen.Home.route) {
            if (!isUserLoggedIn) {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            } else {
                MainScreenWithBottomNav(
                    navController = navController,
                    viewModel = viewModel,
                    onLogout = {
                        isUserLoggedIn = false
                        userEmail = ""
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    selectedSala = selectedSala,
                    onSalaSelected = { selectedSala = it }
                )
            }
        }

        // Pantallas del menú de navegación
        composable(Screen.Calendario.route) {
            if (!isUserLoggedIn) {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.Login.route)
                }
            }
        }

        composable(Screen.Disponibilidad.route) {
            if (!isUserLoggedIn) {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.Login.route)
                }
            }
        }

        composable(Screen.MisReservas.route) {
            if (!isUserLoggedIn) {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.Login.route)
                }
            }
        }

        composable(Screen.Perfil.route) {
            if (!isUserLoggedIn) {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.Login.route)
                }
            }
        }

        // Pantalla de crear nueva reserva
        composable(Screen.Reserva.route) {
            selectedSala?.let { sala ->
                ReservaScreen(
                    sala = sala,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onReservaCreada = {
                        navController.popBackStack()
                    },
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun MainScreenWithBottomNav(
    navController: NavHostController,
    viewModel: ReservaViewModel,
    onLogout: () -> Unit,
    selectedSala: Sala?,
    onSalaSelected: (Sala) -> Unit
) {
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem.Buscar,
        BottomNavItem.Calendario,
        BottomNavItem.Disponibilidad,
        BottomNavItem.MisReservas,
        BottomNavItem.Perfil
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                bottomNavController.navigate(item.route) {
                                    popUpTo(bottomNavController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Buscar.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavItem.Buscar.route) {
                HomeScreen(
                    onNavigateToReserva = { sala ->
                        onSalaSelected(sala)
                        navController.navigate(Screen.Reserva.route)
                    },
                    onNavigateToMisReservas = {
                        bottomNavController.navigate(BottomNavItem.MisReservas.route)
                    },
                    onLogout = onLogout,
                    viewModel = viewModel
                )
            }

            composable(BottomNavItem.Calendario.route) {
                CalendarioScreen(
                    onNavigateBack = {
                        bottomNavController.navigate(BottomNavItem.Buscar.route)
                    },
                    viewModel = viewModel
                )
            }

            composable(BottomNavItem.Disponibilidad.route) {
                DisponibilidadScreen(
                    onNavigateBack = {
                        bottomNavController.navigate(BottomNavItem.Buscar.route)
                    },
                    viewModel = viewModel
                )
            }

            composable(BottomNavItem.MisReservas.route) {
                MisReservasScreen(
                    onNavigateBack = {
                        bottomNavController.navigate(BottomNavItem.Buscar.route)
                    },
                    viewModel = viewModel
                )
            }

            composable(BottomNavItem.Perfil.route) {
                PerfilScreen(
                    onNavigateBack = {
                        bottomNavController.navigate(BottomNavItem.Buscar.route)
                    },
                    onLogout = onLogout,
                    viewModel = viewModel
                )
            }
        }
    }
}

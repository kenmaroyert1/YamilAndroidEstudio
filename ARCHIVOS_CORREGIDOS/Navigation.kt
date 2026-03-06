package com.example.proyecto1.navigation

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
import com.example.proyecto1.model.Sala
import com.example.proyecto1.model.TipoUsuario
import com.example.proyecto1.ui.screens.*
import com.example.proyecto1.viewmodel.ReservaViewModel

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
    val uiState by viewModel.uiState.collectAsState()
    var selectedSala by remember { mutableStateOf<Sala?>(null) }

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { email, tipo ->
                    if (viewModel.login(email, tipo)) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            if (uiState.usuarioActual == null) {
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
                        viewModel.logout()
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    selectedSala = selectedSala,
                    onSalaSelected = { selectedSala = it }
                )
            }
        }

        composable(Screen.Reserva.route) {
            selectedSala?.let { sala ->
                ReservaScreen(
                    sala = sala,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onReservaCreada = {
                        navController.popBackStack()
                        // Podríamos navegar a Mis Reservas aquí
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

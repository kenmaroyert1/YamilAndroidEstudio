package com.example.reservasalas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reservasalas.model.EstadoReserva
import com.example.reservasalas.model.Reserva
import com.example.reservasalas.viewmodel.ReservaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisReservasScreen(
    onNavigateBack: () -> Unit,
    viewModel: ReservaViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val misReservas = viewModel.obtenerMisReservas()
    var showCancelDialog by remember { mutableStateOf<Reserva?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Reservas") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (misReservas.isEmpty()) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Default.EventBusy,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "No tienes reservas",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        "Explora las salas disponibles y crea tu primera reserva",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Agrupar por estado
                val reservasActivas = misReservas.filter { 
                    it.estado == EstadoReserva.CONFIRMADA || it.estado == EstadoReserva.PENDIENTE 
                }
                val reservasPasadas = misReservas.filter { 
                    it.estado == EstadoReserva.COMPLETADA || it.estado == EstadoReserva.CANCELADA 
                }

                if (reservasActivas.isNotEmpty()) {
                    item {
                        Text(
                            "Reservas Activas",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    
                    items(reservasActivas) { reserva ->
                        ReservaCard(
                            reserva = reserva,
                            onCancelar = { showCancelDialog = reserva }
                        )
                    }
                }

                if (reservasPasadas.isNotEmpty()) {
                    item {
                        Text(
                            "Historial",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                        )
                    }
                    
                    items(reservasPasadas) { reserva ->
                        ReservaCard(
                            reserva = reserva,
                            onCancelar = null
                        )
                    }
                }
            }
        }

        // Diálogo de cancelación
        showCancelDialog?.let { reserva ->
            AlertDialog(
                onDismissRequest = { showCancelDialog = null },
                icon = { Icon(Icons.Default.Warning, null) },
                title = { Text("Cancelar Reserva") },
                text = {
                    Text("¿Estás seguro de que deseas cancelar la reserva de ${reserva.sala.nombre} para el ${reserva.formatearFecha()}?")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.cancelarReserva(reserva.id)
                            showCancelDialog = null
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text("Cancelar Reserva")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showCancelDialog = null }) {
                        Text("Volver")
                    }
                }
            )
        }
    }
}

@Composable
fun ReservaCard(
    reserva: Reserva,
    onCancelar: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (reserva.estado) {
                EstadoReserva.CONFIRMADA -> MaterialTheme.colorScheme.surface
                EstadoReserva.PENDIENTE -> MaterialTheme.colorScheme.surfaceVariant
                EstadoReserva.CANCELADA -> MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f)
                EstadoReserva.COMPLETADA -> MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = reserva.sala.nombre,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${reserva.sala.edificio} - Piso ${reserva.sala.piso}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    color = when (reserva.estado) {
                        EstadoReserva.CONFIRMADA -> MaterialTheme.colorScheme.primaryContainer
                        EstadoReserva.PENDIENTE -> MaterialTheme.colorScheme.secondaryContainer
                        EstadoReserva.CANCELADA -> MaterialTheme.colorScheme.errorContainer
                        EstadoReserva.COMPLETADA -> MaterialTheme.colorScheme.tertiaryContainer
                    },
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = reserva.estado.name,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(reserva.formatearFecha())
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Schedule,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(reserva.formatearHora())
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("${reserva.participantes} participantes")
            }

            if (reserva.proposito.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Propósito: ${reserva.proposito}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Botón de cancelar (solo para reservas activas)
            onCancelar?.let {
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(
                    onClick = it,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Icon(Icons.Default.Cancel, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cancelar Reserva")
                }
            }
        }
    }
}

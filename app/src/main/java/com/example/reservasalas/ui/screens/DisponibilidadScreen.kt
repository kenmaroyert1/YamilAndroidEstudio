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
import com.example.reservasalas.model.Sala
import com.example.reservasalas.model.TipoSala
import com.example.reservasalas.viewmodel.ReservaViewModel
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisponibilidadScreen(
    onNavigateBack: () -> Unit,
    viewModel: ReservaViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTipo by remember { mutableStateOf<TipoSala?>(null) }
    var showOnlyAvailable by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Disponibilidad de Salas") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Panel de información
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Consulta en tiempo real",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Verifica la disponibilidad actual de todas las salas",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            // Filtros
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedTipo == null,
                    onClick = { selectedTipo = null },
                    label = { Text("Todas") },
                    leadingIcon = {
                        if (selectedTipo == null) Icon(Icons.Default.Check, null, Modifier.size(18.dp))
                    }
                )
                
                TipoSala.entries.forEach { tipo ->
                    FilterChip(
                        selected = selectedTipo == tipo,
                        onClick = { selectedTipo = if (selectedTipo == tipo) null else tipo },
                        label = { Text(tipo.name) },
                        leadingIcon = {
                            if (selectedTipo == tipo) Icon(Icons.Default.Check, null, Modifier.size(18.dp))
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Toggle para mostrar solo disponibles
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Mostrar solo disponibles ahora")
                Switch(
                    checked = showOnlyAvailable,
                    onCheckedChange = { showOnlyAvailable = it }
                )
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Estadísticas rápidas
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val salas = uiState.salas.filter { 
                    selectedTipo == null || it.tipo == selectedTipo 
                }
                val disponibles = salas.count { it.disponible }
                
                EstadisticaCard(
                    titulo = "Total",
                    valor = salas.size.toString(),
                    icono = Icons.Default.MeetingRoom
                )
                
                EstadisticaCard(
                    titulo = "Disponibles",
                    valor = disponibles.toString(),
                    icono = Icons.Default.CheckCircle,
                    color = MaterialTheme.colorScheme.primary
                )
                
                EstadisticaCard(
                    titulo = "Ocupadas",
                    valor = (salas.size - disponibles).toString(),
                    icono = Icons.Default.Cancel,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de salas con disponibilidad
            val salasFiltradas = uiState.salas
                .filter { selectedTipo == null || it.tipo == selectedTipo }
                .filter { !showOnlyAvailable || it.disponible }

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(salasFiltradas) { sala ->
                    DisponibilidadSalaCard(
                        sala = sala,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun EstadisticaCard(
    titulo: String,
    valor: String,
    icono: androidx.compose.ui.graphics.vector.ImageVector,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icono,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = valor,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = titulo,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun DisponibilidadSalaCard(
    sala: Sala,
    viewModel: ReservaViewModel,
    modifier: Modifier = Modifier
) {
    val ahora = LocalTime.now()
    val hoy = LocalDate.now()
    val disponibleAhora = viewModel.verificarDisponibilidad(
        sala.id, hoy, ahora, ahora.plusHours(1)
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (disponibleAhora) 
                MaterialTheme.colorScheme.surface 
            else 
                MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = sala.nombre,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        color = if (disponibleAhora) 
                            MaterialTheme.colorScheme.primaryContainer 
                        else 
                            MaterialTheme.colorScheme.errorContainer,
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = if (disponibleAhora) "DISPONIBLE" else "OCUPADA",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "${sala.edificio} - Piso ${sala.piso}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${sala.capacidad}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            when (sala.tipo) {
                                TipoSala.AULA -> Icons.Default.School
                                TipoSala.LABORATORIO -> Icons.Default.Science
                                TipoSala.SALA_ESTUDIO -> Icons.Default.MenuBook
                                TipoSala.CABINA -> Icons.Default.Videocam
                            },
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = sala.tipo.name,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            
            Icon(
                if (disponibleAhora) Icons.Default.CheckCircle else Icons.Default.Cancel,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = if (disponibleAhora) 
                    MaterialTheme.colorScheme.primary 
                else 
                    MaterialTheme.colorScheme.error
            )
        }
    }
}

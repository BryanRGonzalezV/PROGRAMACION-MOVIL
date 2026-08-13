package com.ejemplo.aplicaciondeprogramacionmovil.ui.navigation.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.ejemplo.aplicaciondeprogramacionmovil.data.Tarea

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaScreen(
    listaTareas: List<Tarea>,
    onNavegarADetalle: (Int) -> Unit,
    onNavegarAHistorial: () -> Unit,
    onAgregarTarea: (String) -> Unit,
    onEliminarTarea: (Tarea) -> Unit,
    onActualizarTarea: (Int, Tarea) -> Unit
) {
    var textoTarea by remember { mutableStateOf("") }
    val context = LocalContext.current

    val tareasCompletadas = listaTareas.count { it.completada }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Pendientes") },
                actions = {
                    IconButton(onClick = onNavegarAHistorial) {
                        Text("📜")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textoTarea,
                    onValueChange = { textoTarea = it },
                    label = { Text("Escribe una tarea...") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (textoTarea.isNotBlank()) {
                            onAgregarTarea(textoTarea)
                            Toast.makeText(
                                context,
                                "Tarea agregada correctamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            textoTarea = ""
                        }
                    },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("➕ Agregar")
                }
            }

            Text(
                text = "Completadas: $tareasCompletadas de ${listaTareas.size}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = {
                    if (listaTareas.isEmpty()) 0f
                    else tareasCompletadas.toFloat() / listaTareas.size.toFloat()
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(listaTareas) { index, tarea ->
                    ItemTareaCard(
                        tarea = tarea,
                        onCheckedChange = { checked ->
                            onActualizarTarea(index, tarea.copy(completada = checked))
                        },
                        onEliminar = {
                            onEliminarTarea(tarea)
                        },
                        onClickTarea = {
                            onNavegarADetalle(tarea.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ItemTareaCard(
    tarea: Tarea,
    onCheckedChange: (Boolean) -> Unit,
    onEliminar: () -> Unit,
    onClickTarea: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickTarea() },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = tarea.completada,
                onCheckedChange = onCheckedChange
            )

            Text(
                text = tarea.titulo,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                textDecoration = if (tarea.completada) TextDecoration.LineThrough else TextDecoration.None
            )

            IconButton(onClick = onEliminar) {
                Text(
                    text = "❌",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
package com.ejemplo.aplicaciondeprogramacionmovil.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ejemplo.aplicaciondeprogramacionmovil.ui.navigation.screen.DetalleScreen
import com.ejemplo.aplicaciondeprogramacionmovil.ui.navigation.screen.HistorialScreen
import com.ejemplo.aplicaciondeprogramacionmovil.ui.navigation.screen.InicioScreen
import com.ejemplo.aplicaciondeprogramacionmovil.ui.navigation.screen.ListaScreen
import com.ejemplo.aplicaciondeprogramacionmovil.ui.viewmodel.TareaViewModel

sealed class Pantalla(val ruta: String) {
    object Inicio : Pantalla("inicio")
    object Lista : Pantalla("lista")
    object Historial : Pantalla("historial")
    object Detalle : Pantalla("detalle/{itemId}") {
        fun crearRuta(id: Int) = "detalle/$id"
    }
}

@Composable
fun AppNavigation(viewModel: TareaViewModel = viewModel()) {
    val navController = rememberNavController()
    val listaTareas = viewModel.listaTareas

    NavHost(
        navController = navController,
        startDestination = Pantalla.Inicio.ruta
    ) {
        composable(Pantalla.Inicio.ruta) {
            InicioScreen(
                totalTareas = listaTareas.size
            ) { navController.navigate(Pantalla.Lista.ruta) }
        }

        composable(Pantalla.Lista.ruta) {
            ListaScreen(
                listaTareas = listaTareas,
                onNavegarADetalle = { id ->
                    navController.navigate(Pantalla.Detalle.crearRuta(id))
                },
                onNavegarAHistorial = {
                    navController.navigate(Pantalla.Historial.ruta)
                },
                onAgregarTarea = { titulo ->
                    viewModel.agregarTarea(titulo)
                },
                onEliminarTarea = { tarea ->
                    viewModel.eliminarTarea(tarea)
                },
                onActualizarTarea = { index, tarea ->
                    viewModel.actualizarTarea(index, tarea)
                },
            )
        }

        composable(Pantalla.Historial.ruta) {
            HistorialScreen(
                tareasCompletadas = listaTareas.filter { it.completada }
            ) { navController.popBackStack() }
        }

        composable(
            route = Pantalla.Detalle.ruta,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("itemId") ?: 0
            val tareaEncontrada = listaTareas.find { it.id == id }

            DetalleScreen(
                tarea = tareaEncontrada,
                onVolver = { navController.popBackStack() }
            )
        }
    }
}
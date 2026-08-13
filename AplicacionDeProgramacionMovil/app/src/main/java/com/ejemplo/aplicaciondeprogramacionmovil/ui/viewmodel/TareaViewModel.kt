package com.ejemplo.aplicaciondeprogramacionmovil.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.ejemplo.aplicaciondeprogramacionmovil.data.Tarea
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TareaViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("tareas_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    
    val listaTareas = mutableStateListOf<Tarea>()

    init {
        cargarTareas()
    }

    private fun cargarTareas() {
        val json = sharedPreferences.getString("lista_tareas", null)
        if (json != null) {
            val type = object : TypeToken<List<Tarea>>() {}.type
            val tareasGuardadas: List<Tarea> = gson.fromJson(json, type)
            listaTareas.clear()
            listaTareas.addAll(tareasGuardadas)
        } else {
            // Tareas iniciales por defecto si no hay nada guardado
            listaTareas.addAll(
                listOf(
                    Tarea(1, "Repasar Kotlin y Compose", completada = true),
                    Tarea(2, "Completar la Práctica 12", completada = false),
                    Tarea(3, "Subir cambios a GitHub", completada = false)
                )
            )
            guardarTareas()
        }
    }

    fun guardarTareas() {
        val json = gson.toJson(listaTareas.toList())
        sharedPreferences.edit().putString("lista_tareas", json).apply()
    }

    fun agregarTarea(titulo: String) {
        val nuevoId = if (listaTareas.isEmpty()) 1 else listaTareas.maxOf { it.id } + 1
        listaTareas.add(Tarea(nuevoId, titulo))
        guardarTareas()
    }

    fun eliminarTarea(tarea: Tarea) {
        listaTareas.remove(tarea)
        guardarTareas()
    }

    fun actualizarTarea(index: Int, tarea: Tarea) {
        listaTareas[index] = tarea
        guardarTareas()
    }
}

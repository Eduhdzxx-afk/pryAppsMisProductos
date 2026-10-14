package com.example.pryappsmisproductos.Presentador

import com.example.pryappsmisproductos.Modelo.clsCategoriasModelo
import com.example.pryappsmisproductos.Modelo.dataCategorias
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCategoriasPresentador {
    private val modelo = clsCategoriasModelo()
    private var listaCategorias: List<dataCategorias> = emptyList()

    fun iniciar() {

        // Se usa un Scope simple para no bloquear la interfaz
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Aqui recibes el RETURN directo del modelo
                listaCategorias = modelo.obtenerCategorias()
                vista.mostrarCategorias(listaCategorias)

            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCategoria(posicion: Int) {
        if (posicion > 0 && posicion < listaCategorias.size) {
            val categoria = listaCategorias[posicion]
            vista.mostrarMensajeExito(categoria.id, categoria.categoria)
        }
    }
}
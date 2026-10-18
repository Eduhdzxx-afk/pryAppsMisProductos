package com.example.pryappsmisproductos.Presentador

import com.example.pryappsmisproductos.Modelo.clsCategoriasModelo
import com.example.pryappsmisproductos.Modelo.clsProductosModelo
import com.example.pryappsmisproductos.Modelo.dataCategorias
import com.example.pryappsmisproductos.Modelo.dataProductos
import com.example.pryappsmisproductos.Vista.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCategoriasPresentador(val vista: MainActivity){
    private val modeloCategorias = clsCategoriasModelo()
    private val modeloProductos = clsProductosModelo()
    private var listaCategorias: List<dataCategorias> = emptyList()

    fun iniciar() {
        // Se usa un Scope simple para no bloquear la interfaz
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // 1. Cargar la lista de categorias en el Spinner
                listaCategorias = modeloCategorias.obtenerCategorias()
                vista.mostrarCategorias(listaCategorias)

                //2. Cargar TODOS los productos por defecto (categoria_id=0)
                cargarProductos(0)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCategoria(posicion: Int) {
        if (posicion >= 0 && posicion < listaCategorias.size) {
            val categoria = listaCategorias[posicion]
            //Si categoria.id es 0, la API traera todos; si es>0, filtrara por esa categoria
            cargarProductos(categoria.id)
        }
    }

    private fun cargarProductos(categoriaId: Int){
        CoroutineScope(Dispatchers.Main).launch{
            try{
                val productos = modeloProductos.obtenerProductosPorCategoria(categoriaId)
                vista.mostrarProductos(productos)
            } catch(e: Exception){
                vista.mostrarError("Error al cargar productos: "+e.localizedMessage)
            }
        }
    }

    fun seleccionarProducto(producto: dataProductos){
        val precioDouble = producto.precio.toDoubleOrNull() ?: 0.0
        vista.abrirDetalleProducto(producto.id, producto.nombre, producto.descripcion, precioDouble)
    }
}
package com.example.pryappsmisproductos.Presentador

import com.example.pryappsmisproductos.Modelo.clsProductosModelo
import com.example.pryappsmisproductos.Vista.DetalleProductoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsDetalleProductoPresentador(private val vista: DetalleProductoActivity) {
    private val modelo = clsProductosModelo()

    fun cargarDetalle(productoId: Int){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                //Aqui se consume obtenerDetalleProducto del modelo
                val listaImagenes: List<dataImagenesProducto> = modelo.obtenerDetalleProducto(productoId)
                vista.mostrarImagenes(listaImagenes)
            }catch(e: Exception ){
                vista.mostrarError("Error al cargar detalle: " + e.localizedMessage)
            }
        }
    }
}
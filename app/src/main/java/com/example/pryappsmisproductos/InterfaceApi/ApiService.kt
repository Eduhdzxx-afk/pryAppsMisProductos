package com.example.pryappsmisproductos.InterfaceApi

import com.example.pryappsmisproductos.Modelo.dataCategorias
import com.example.pryappsmisproductos.Modelo.dataImagenesProducto
import com.example.pryappsmisproductos.Modelo.dataProductos
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("obtener_categorias.php") //Este es el Api en php
    suspend fun obtenerCategorias(): List<dataCategorias>
    // aqui agregamos los EndPoints para el recycler y para la vista detalle
    @GET("obtener_productos.php")
    suspend fun obtenerProductos(@Query("categoria_id") categoriaId : Int): List<dataProductos>
    @GET("detalle_producto.php")
    suspend fun detalleProducto(@Query("producto_id") productoId: Int): List<dataImagenesProducto>
}  
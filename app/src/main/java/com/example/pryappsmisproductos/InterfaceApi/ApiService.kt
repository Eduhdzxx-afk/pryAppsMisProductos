package com.example.pryappsmisproductos.InterfaceApi

import android.R
import com.example.pryappsmisproductos.Modelo.dataCategorias
import com.example.pryappsmisproductos.Modelo.dataImagenesProducto
import com.example.pryappsmisproductos.Modelo.dataProductos
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("obtener_categorias.php") //Este es el Api en php
    suspend fun obtenerCategorias(): List<dataCategorias>
    // aqui agregamos los EndPoints para el recycler y para la vista detalle
    @GET("obtener_productos.php")
    suspend fun obtenerProductos(@Query("categoria_id") categoriaId : Int): List<dataProductos>
    @GET("detalle_producto.php")
    suspend fun detalleProducto(@Query("producto_id") productoId: Int): List<dataImagenesProducto>
    @POST("login.php")
    suspend fun iniciarSesion(@Body request: LoginRequest): LoginResponde
    @Serializable
    data class LoginRequest(
        @SerialName("nombreUsuario") val nombreUsuario: String="",
        @SerialName("passusuario") val passusuario: String=""
    )
    @Serializable
    data class LoginResponde(
        @SerialName("exito") val exito: Boolean= false,
        @SerialName("mensaje") val mensaje: String="",
        @SerialName("idUsuario") val idUsuario: Int = 0,
        @SerialName("nombreUsuario") val nombreUsuario: String="",
        @SerialName("nombreCliente") val nombreCliente: String="",
        @SerialName("telefono") val telefono: String=""
    )
}  
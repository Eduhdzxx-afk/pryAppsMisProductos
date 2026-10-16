package com.example.pryappsmisproductos.InterfaceApi

import com.example.pryappsmisproductos.Modelo.dataCategorias
import retrofit2.http.GET

interface ApiService {
    @GET("obtener_categorias.php") //Este es el Api en php
    suspend fun obtenerCategorias(): List<dataCategorias>
}  
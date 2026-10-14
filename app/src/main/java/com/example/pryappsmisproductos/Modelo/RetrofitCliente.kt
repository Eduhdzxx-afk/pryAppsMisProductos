package com.example.pryappsmisproductos.Modelo

import com.example.pryappsmisproductos.InterfaceApi.ApiService
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitCliente {
    private const val BASE_URL="https://192.168.1.69/tienda_api/"

    private val jsonConfig = Json{
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }

    val apiService = ApiService by lazy{
        val contenType = "application/json".toMediaType
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConfig.asConverterFactory(contenType))
            .build()
            .create(ApiService::class.java)
    }

}
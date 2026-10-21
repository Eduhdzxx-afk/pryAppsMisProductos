package com.example.pryappsmisproductos.Modelo

import com.example.pryappsmisproductos.InterfaceApi.ApiService
import okhttp3.Request

class clsLoginModelo {
    suspend fun autenticarUsuario(usuario: String, clave: String): ApiService.LoginResponde{
        val request = ApiService.LoginRequest(nombreUsuario = usuario, passusuario = clave)
        return RetrofitCliente.apiService.iniciarSesion(request)
    }
}
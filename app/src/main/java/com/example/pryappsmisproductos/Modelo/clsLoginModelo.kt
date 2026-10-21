package com.example.pryappsmisproductos.Modelo

import okhttp3.Request

class clsLoginModelo {
    suspend fun autenticarUsuario(usuario: String, clave: String): LoginResponse{
        val request = LoginRequest(nombreUsuario = usuario, passusuario = clave)
        return RetrofitCliente.apiService.iniciarSesion(Request)
    }
}
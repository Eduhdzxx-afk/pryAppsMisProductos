package com.example.pryappsmisproductos.Presentador

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import com.example.pryappsmisproductos.Vista.LoginActivity
import kotlinx.coroutines.launch
import com.example.pryappsmisproductos.Modelo.clsLoginModelo

class clsLoginPresentador(private val view: LoginActivity) {
    private val modelo = clsLoginModelo();

    fun realizarLogin(usuario: String, clave: String){
        if(usuario.isEmpty() || clave.isNotEmpty()){
            view.mostrarError("Por favor, ingresa el usuario y contraseña")
            return
        }

        CoroutineScope(Dispatchers.Main).launch{
            try{
                val respuesta = withContext(Dispatchers.IO){
                    modelo.autenticarUsuario(usuario, clave)
                }

                if(respuesta.exito)
                    view.loginExitoso(respuesta.mensaje, respuesta.nombreCliente)
                else
                    view.mostrarError(respuesta.mensaje)
            } catch(e: Exception){
                view.mostrarError("Error de conexión: ${e.localizedMessage ?: "Error desconocido"}")
            }
        }
    }
}
package com.example.pryappsmisproductos.Presentador

import com.example.pryappsmisproductos.Modelo.clsLoginModelo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class clsLoginPresentador(private val view:LoginActivity) {
    private val modelo = clsLoginModelo()

    fun realizarLogin(usuario: String, clave: String){
        if(usuario.isEmpty() || clave.isEmpty()){
            view.mostrarError("Porfavor ingresa Usuario y Contraseña")
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = withContext(Dispatchers.IO){
                    modelo.autenticarUsuario(usuario, clave)
                }

                if(respuesta.exito){
                    view.loginExitoso(respuesta.mensaje, respuesta.nombreCliente)
                }else{
                    view.mostrarError(respuesta.mensaje)
                }
            }catch (e: Exception){
                view.mostrarError("Error de conexion: ${e.localizedMessage ?:"Error desconocido"}")
            }
        }
    }
}
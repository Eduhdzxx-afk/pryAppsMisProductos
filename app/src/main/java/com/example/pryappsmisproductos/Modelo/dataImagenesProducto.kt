package com.example.pryappsmisproductos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataImagenesProducto(
    @SerialName("id") val id: Int = 0,
    @SerialName("producto_id") val productoId: Int = 0,
    @SerialName("url_imagen") val urlImagen: String = ""
)

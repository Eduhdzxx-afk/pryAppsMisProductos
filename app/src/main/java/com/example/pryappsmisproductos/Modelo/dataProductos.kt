package com.example.pryappsmisproductos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataProductos(
    @SerialName("id") val id: Int = 0,
    @SerialName("categoria_id") val categoriaid: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("descripcion") val descripcion: String = "",
    @SerialName("precio") val precio: String = "0.0",
    @SerialName("url_imagen_principal") val urlimagenprincipal: String = ""
)

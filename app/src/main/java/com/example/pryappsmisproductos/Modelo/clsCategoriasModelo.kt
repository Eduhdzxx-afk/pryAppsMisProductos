package com.example.pryappsmisproductos.Modelo

class clsCategoriasModelo {
    suspend fun obtenerCategorias():List<dataCategorias>{
        val datos = RetrofitCliente.apiService.obtenerCategorias() //Aquí hace el pedido a la BD

        val listaFinal = mutableListOf(dataCategorias(0, "Todas las categorias"))
        listaFinal.addAll(datos)

        return listaFinal //Retorna la lista directamente
    }
}
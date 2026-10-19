package com.example.pryappsmisproductos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pryappsmisproductos.Modelo.dataImagenesProducto
import com.example.pryappsmisproductos.R

class GaleriaAdaptador(private var listaImagenes: List<dataImagenesProducto>): RecyclerView.Adapter<GaleriaAdaptador.GaleriaViewHolder>() {


    // Antes tenemos que crear una clase holder
    class GaleriaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        // Lo dejamos pendiente primero inflamos el layout
        val imgGaleria: ImageView = itemView.findViewById(R.id.imgGaleria)
    }

    // Estas 3 funciones se van a programar. Son contratos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleriaViewHolder {
        // Vamos a inflar el layout item_galeria.xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_galeria,parent,false)
        return GaleriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: GaleriaViewHolder, position: Int) {
        val imagen = listaImagenes[position]
        val baseUrl = "http://192.168.1.69/tienda_api/"
        val urlCompleta = baseUrl + imagen.urlImagen

        Glide.with(holder.itemView.context)
            .load(urlCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgGaleria)
    }

    override fun getItemCount(): Int {
        return listaImagenes.size
    }

    fun actualizarLista(nuevaLista: List<dataImagenesProducto>)
    {
        listaImagenes = nuevaLista
        notifyDataSetChanged()
    }

}
package com.example.pryappsmisproductos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pryappsmisproductos.R
import com.example.pryappsmisproductos.Modelo.dataProductos
import com.example.pryappsmisproductos.Vista.MainActivity

    class ProductosAdaptador(
        private val activity: MainActivity,
        private var listaProductos: List<dataProductos>
    ) : RecyclerView.Adapter<ProductosAdaptador.ProductoViewHolder>() {
        class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvNombre: TextView = itemView.findViewById(R.id.txtProducto)
            val tvPrecio: TextView = itemView.findViewById(R.id.txtPrecio)
            val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
            return ProductoViewHolder(view)
        }

        override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
            val producto = listaProductos[position]
            holder.tvNombre.text = producto.nombre
            holder.tvPrecio.text = "$" + producto.precio.toString()
            val baseUrl = "http://192.168.137.142/tienda_api/"
            val urlImagenCompleta = baseUrl + producto.urlimagenprincipal
            Glide.with(holder.itemView.context)
                .load(urlImagenCompleta)
                .placeholder(android.R.drawable.ic_menu_gallery) // Imagen por defecto mientras carga
                .error(android.R.drawable.stat_notify_error) // Imagen si falla la carga
                .into(holder.imgProducto)

            holder.itemView.setOnClickListener(object: View.OnClickListener {
                override fun onClick(v: View?) {
                    //Esta funcion se ejecuta en la vista MainActivity
                    // y se manda al presentador para que se muestre el prod seleccionado
                    activity.seleccionarProducto(producto)
                }
            })
        }
        override fun getItemCount(): Int {
            return listaProductos.size
        }

        fun actualizarLista(nuevaLista: List<dataProductos>) {
            listaProductos = nuevaLista
            notifyDataSetChanged()
        }
    }
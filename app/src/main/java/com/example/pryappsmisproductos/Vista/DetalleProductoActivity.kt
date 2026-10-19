package com.example.pryappsmisproductos.Vista

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.pryappsmisproductos.Adaptador.GaleriaAdaptador
import com.example.pryappsmisproductos.Modelo.dataImagenesProducto
import com.example.pryappsmisproductos.Presentador.clsDetalleProductoPresentador
import com.example.pryappsmisproductos.R

class DetalleProductoActivity : AppCompatActivity() {
    private lateinit var presenter: clsDetalleProductoPresentador
    private lateinit var vpGaleria: ViewPager2
    private lateinit var txtPrecio: TextView
    private lateinit var txtNombre: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var adapterGaleria: GaleriaAdaptador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_producto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        // 1. Enlazar componentes XML
        vpGaleria = findViewById(R.id.vpGaleria)
        txtPrecio = findViewById(R.id.txtdetallePrecio)
        txtNombre = findViewById(R.id.txtdetalleProducto)
        txtDescripcion = findViewById(R.id.txtdetalleDescripcion)
        adapterGaleria = GaleriaAdaptador(emptyList())
        vpGaleria.adapter = adapterGaleria

        // 2. Obtener los datos del producto enviados desde MainActivity
        val id = intent.getIntExtra("PRODUCTO_ID", 0)
        val nombre = intent.getStringExtra("NOMBRE_PRODUCTO") ?: ""
        val descripcion = intent.getStringExtra("DESCRIPCION_PRODUCTO") ?: "No llego"
        val precio = intent.getDoubleExtra("PRECIO_PRODUCTO", 0.0)

        //Se los ponemos a los textView
        txtNombre.text = nombre
        txtPrecio.text = "$" + precio.toString()
        txtDescripcion.text = descripcion

        //Inicializar presentador y cargar la galería
        presenter = clsDetalleProductoPresentador(this)

        if (id != 0) {
            presenter.cargarDetalle(id)
        }
    }
    fun mostrarimagenes(imagenes: List<dataImagenesProducto>) {
        adapterGaleria.actualizarLista(imagenes)
    }
    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}

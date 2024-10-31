package com.dam2024m8uf1_tfinal.drivera

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dam2024m8uf1_tfinal.drivera.Singletoon.Videojuego
import com.dam2024m8uf1_tfinal.drivera.Singletoon.VideojuegoManager
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var videojuegoAdapter: VideojuegoAdapter
    private lateinit var recyclerView: RecyclerView
    private val videojuegoManager = VideojuegoManager.getInstance()
    private val videojuegos = mutableListOf<Videojuego>() // Lista mutable para agregar elementos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configurar el adaptador
        videojuegoAdapter = VideojuegoAdapter(videojuegos, { videojuego -> editVideojuego(videojuego) }, { videojuego -> deleteVideojuego(videojuego) })
        recyclerView.adapter = videojuegoAdapter

        // Cargar videojuegos desde VideojuegoManager
        cargarVideojuegos()

        // Botón para añadir un nuevo videojuego
        val btnAddVideojuego: Button = findViewById(R.id.btnAdd) // Asegúrate de tener un botón en tu layout
        btnAddVideojuego.setOnClickListener {
            val intent = Intent(this, Menu::class.java).apply {
                putExtra("ACCION", "AÑADIR")
            }
            startActivity(intent)
        }
    }

    private fun cargarVideojuegos() {
        // Limpia la lista antes de cargar
        videojuegos.clear()
        videojuegos.addAll(videojuegoManager.getVideojuegos()) // Carga la lista de videojuegos desde VideojuegoManager
        videojuegoAdapter.notifyDataSetChanged() // Notifica al adaptador que los datos han cambiado
    }

    private fun editVideojuego(videojuego: Videojuego) {
        // Lógica para editar un videojuego
        val intent = Intent(this, Menu::class.java).apply {
            putExtra("VIDEOJUEGO_ID", videojuego.id) // Pasa el ID del videojuego que se va a editar
        }
        startActivity(intent)
    }

    private fun deleteVideojuego(videojuego: Videojuego) {
        // Lógica para eliminar un videojuego
        videojuegoManager.eliminarVideojuego(videojuego.id) // Usa el método de VideojuegoManager para eliminar el videojuego
        cargarVideojuegos() // Recargar la lista después de eliminar
    }
}

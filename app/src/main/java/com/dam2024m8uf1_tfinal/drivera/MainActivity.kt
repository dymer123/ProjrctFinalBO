package com.dam2024m8uf1_tfinal.drivera

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dam2024m8uf1_tfinal.drivera.Singletoon.Videojuego
import com.dam2024m8uf1_tfinal.drivera.Singletoon.VideojuegoManager

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAdd: Button
    private lateinit var videojuegoAdapter: VideojuegoAdapter
    private lateinit var videojuegos: MutableList<Videojuego>

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        btnAdd = findViewById(R.id.btnAdd)

        // Inicializar la lista de videojuegos
        videojuegos = VideojuegoManager.getInstance().getVideojuegos() // Método que devuelve la lista de videojuegos

        // Configurar el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        videojuegoAdapter = VideojuegoAdapter(videojuegos, ::onEditVideojuego, ::onDeleteVideojuego)
        recyclerView.adapter = videojuegoAdapter

        // Configurar el botón para añadir videojuegos
        btnAdd.setOnClickListener {
            // Redirigir a Menu para añadir un nuevo videojuego
            startActivity(Intent(this, Menu::class.java))
        }
    }

    private fun onEditVideojuego(videojuego: Videojuego) {
        VideojuegoManager.getInstance()
        startActivity(Intent(this, Menu::class.java))
    }

    private fun onDeleteVideojuego(videojuego: Videojuego) {
        // Eliminar el videojuego del manager y actualizar la lista
        VideojuegoManager.getInstance().eliminarVideojuego(videojuego.id) // Método para eliminar
        videojuegos.remove(videojuego)
        videojuegoAdapter.notifyDataSetChanged() // Notificar al adaptador sobre el cambio
        Toast.makeText(this, "Videojuego eliminado.", Toast.LENGTH_SHORT).show()
    }
}
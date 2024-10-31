package com.dam2024m8uf1_tfinal.drivera

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dam2024m8uf1_tfinal.drivera.Singletoon.Videojuego
import com.dam2024m8uf1_tfinal.drivera.Singletoon.VideojuegoManager

class Menu : AppCompatActivity() {
    private lateinit var etTitulo: EditText
    private lateinit var etPrecio: EditText
    private lateinit var spGenero: Spinner
    private lateinit var etCalificacion: EditText
    private lateinit var etDesarrollador: EditText
    private lateinit var spClasificacionEdad: Spinner
    private lateinit var cbMultijugador: CheckBox
    private lateinit var btnActualizar: Button
    private lateinit var btnMostrarDetalles: Button
    private lateinit var btnSeguirEditando: Button

    private val videojuegoManager = VideojuegoManager.getInstance()
    private var currentVideojuegoId: Int? = null // ID del videojuego actual (si se está editando uno)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.menu)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar los componentes de la interfaz
        etTitulo = findViewById(R.id.etTitulo)
        etPrecio = findViewById(R.id.etPrecio)
        spGenero = findViewById(R.id.spGenero)
        etCalificacion = findViewById(R.id.etCalificacion)
        etDesarrollador = findViewById(R.id.etDesarrollador)
        spClasificacionEdad = findViewById(R.id.spClasificacionEdad)
        cbMultijugador = findViewById(R.id.cbMultijugador)
        btnActualizar = findViewById(R.id.btnActualizar)
        btnMostrarDetalles = findViewById(R.id.btnMostrarDetalles)
        btnSeguirEditando = findViewById(R.id.btnSeguirEditando) // Botón para continuar editando

        // Configurar el Spinner de géneros
        val generos = arrayOf("Acción", "Aventura", "RPG", "Deportes", "Estrategia")
        val generoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, generos)
        generoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spGenero.adapter = generoAdapter

        // Configurar el Spinner de clasificación por edad
        val clasificaciones = arrayOf("TP", "+3", "+7", "+12", "+16", "+18")
        val clasificacionAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, clasificaciones)
        clasificacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spClasificacionEdad.adapter = clasificacionAdapter

        // Configurar eventos de botones
        btnActualizar.setOnClickListener {
            actualizarVideojuego()
        }

        btnMostrarDetalles.setOnClickListener {
            mostrarDetalles()
        }

        btnSeguirEditando.setOnClickListener {
            actualizarVideojuego()
            val intent = Intent(this, Menu2::class.java)
            startActivity(intent)
        }

        // Verificar si se pasa un ID de videojuego o una acción
        val accion = intent.getStringExtra("ACCION")
        if (accion == "AÑADIR") {
            añadirVideojuegoVacio() // Inicializar un videojuego vacío
        } else {
            currentVideojuegoId = intent.getIntExtra("VIDEOJUEGO_ID", -1).takeIf { it != -1 }
            cargarVideojuego(currentVideojuegoId)
        }
    }

    private fun cargarVideojuego(id: Int?) {
        Log.d("Menu", "Cargando videojuego con ID: $id")
        id?.let {
            val detalles = videojuegoManager.mostrarDetalles(it)
            Log.d("Menu", "Detalles obtenidos: $detalles")
            Toast.makeText(this, detalles, Toast.LENGTH_LONG).show()
        }
    }

    private fun actualizarVideojuego() {
        val titulo = etTitulo.text.toString()
        val precio = etPrecio.text.toString().toDoubleOrNull()
        val genero = spGenero.selectedItem.toString()
        val calificacion = etCalificacion.text.toString().toFloatOrNull()
        val desarrollador = etDesarrollador.text.toString()
        val clasificacionEdad = spClasificacionEdad.selectedItem.toString()
        val esMultijugador = cbMultijugador.isChecked // Ahora ya es Boolean

        // Validación de entradas
        if (titulo.isBlank() || precio == null || genero.isBlank() || calificacion == null || desarrollador.isBlank()) {
            Toast.makeText(this, "Por favor, complete todos los campos correctamente.", Toast.LENGTH_SHORT).show()
            return
        }

        // Actualiza los atributos del videojuego
        currentVideojuegoId?.let { id ->
            Log.d("Menu", "Actualizando videojuego con ID: $id")
            videojuegoManager.editarVideojuego(
                id,
                titulo,
                genero,
                null,
                desarrollador,
                null,
                null,
                null,
                precio,
                calificacion,
                null,
                null,
                null,
                if (esMultijugador) 1 else 0,
                null
            )

            Toast.makeText(this, "Videojuego actualizado.", Toast.LENGTH_SHORT).show()
        } ?: run {
            Toast.makeText(this, "No se encontró un videojuego para actualizar.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarDetalles() {
        currentVideojuegoId?.let { id ->
            val detalles = videojuegoManager.mostrarDetalles(id)
            Log.i("DetallesVideojuego", detalles)
            Toast.makeText(this, detalles, Toast.LENGTH_LONG).show()
        } ?: run {
            Toast.makeText(this, "No se encontró un videojuego para mostrar detalles.", Toast.LENGTH_SHORT).show()
        }
    }

    // Método para añadir un videojuego vacío
    private fun añadirVideojuegoVacio() {
        val videojuegoVacio = Videojuego() // Crear un nuevo objeto Videojuego vacío
        videojuegoManager.añadirVideojuegoVacio() // Usa el método del VideojuegoManager para añadir el videojuego vacío
        currentVideojuegoId = videojuegoVacio.id // Asignar el ID del nuevo videojuego vacío
    }
}

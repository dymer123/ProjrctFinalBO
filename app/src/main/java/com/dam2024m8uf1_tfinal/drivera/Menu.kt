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
import androidx.appcompat.app.AppCompatActivity
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
        setContentView(R.layout.menu)

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
            val videojuego = videojuegoManager.obtenerVideojuegoPorId(it) // Obtén el objeto Videojuego por su ID
            videojuego?.let { juego ->
                // Mostrar información usando getters
                etTitulo.setText(juego.getTitulo())
                etPrecio.setText(juego.getPrecio().toString())
                spGenero.setSelection(getGeneroPosition(juego.getGenero()))
                etCalificacion.setText(juego.getCalificacion().toString())
                etDesarrollador.setText(juego.getDesarrollador())
                spClasificacionEdad.setSelection(getClasificacionEdadPosition(juego.getClasificacionEdad()))
                cbMultijugador.isChecked = juego.isEsMultijugador()
            } ?: run {
                Toast.makeText(this, "No se encontró el videojuego.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getGeneroPosition(genero: String): Int {
        val generos = arrayOf("Acción", "Aventura", "RPG", "Deportes", "Estrategia")
        return generos.indexOf(genero)
    }

    private fun getClasificacionEdadPosition(clasificacion: String): Int {
        val clasificaciones = arrayOf("TP", "+3", "+7", "+12", "+16", "+18")
        return clasificaciones.indexOf(clasificacion)
    }

    private fun actualizarVideojuego() {
        val titulo = etTitulo.text.toString()
        val precioStr = etPrecio.text.toString()
        val genero = spGenero.selectedItem.toString()
        val calificacionStr = etCalificacion.text.toString()
        val desarrollador = etDesarrollador.text.toString()
        val clasificacionEdad = spClasificacionEdad.selectedItem.toString()
        val esMultijugador = cbMultijugador.isChecked // Ahora ya es Boolean

        // Validación de entradas
        if (titulo.isBlank() || precioStr.isBlank() || genero.isBlank() || calificacionStr.isBlank() || desarrollador.isBlank()) {
            Toast.makeText(this, "Por favor, complete todos los campos correctamente.", Toast.LENGTH_SHORT).show()
            return
        }

        val precio = precioStr.toDoubleOrNull()
        val calificacion = calificacionStr.toFloatOrNull()

        // Actualiza los atributos del videojuego
        currentVideojuegoId?.let { id ->
            Log.d("Menu", "Actualizando videojuego con ID: $id")
            videojuegoManager.editarVideojuego(
                id,
                titulo,
                genero,
                null, // Aquí puedes pasar la plataforma si tienes
                desarrollador,
                null, // Aquí puedes pasar el año de lanzamiento si tienes
                null, // Aquí puedes pasar el ID del desarrollador si tienes
                null, // Aquí puedes pasar otros campos si tienes
                precio ?: 0.0,
                calificacion ?: 0f,
                null, // Aquí puedes pasar el ID de la clasificación por edad si tienes
                null, // Aquí puedes pasar la fecha de lanzamiento si tienes
                null, // Aquí puedes pasar otros campos si tienes
                if (esMultijugador) 1 else 0,
                null  // Aquí puedes pasar otros campos si tienes
            )

            Toast.makeText(this, "Videojuego actualizado.", Toast.LENGTH_SHORT).show()
        } ?: run {
            Toast.makeText(this, "No se encontró un videojuego para actualizar.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarDetalles() {
        currentVideojuegoId?.let { id ->
            val videojuego = videojuegoManager.obtenerVideojuegoPorId(id)
            videojuego?.let { juego ->
                val detalles = juego.mostrarDetalles() // Utiliza el método mostrarDetalles() de Videojuego
                Log.i("DetallesVideojuego", detalles)
                Toast.makeText(this, detalles, Toast.LENGTH_LONG).show()
            } ?: run {
                Toast.makeText(this, "No se encontró un videojuego para mostrar detalles.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método para añadir un videojuego vacío
    private fun añadirVideojuegoVacio() {
        val videojuegoVacio = Videojuego() // Crear un nuevo objeto Videojuego vacío
        videojuegoManager.añadirVideojuegoVacio() // Usa el método del VideojuegoManager para añadir el videojuego vacío
        currentVideojuegoId = videojuegoVacio.getId() // Asignar el ID del nuevo videojuego vacío
    }
}

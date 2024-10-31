package com.dam2024m8uf1_tfinal.drivera

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dam2024m8uf1_tfinal.drivera.Singletoon.VideojuegoManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Menu2 : AppCompatActivity() {
    private lateinit var etPlataforma: EditText
    private lateinit var etPublicador: EditText
    private lateinit var cbModoHistoria: CheckBox
    private lateinit var etFechaLanzamiento: EditText
    private lateinit var etNumeroJugadores: EditText
    private lateinit var etDuracionPromedio: EditText
    private lateinit var etMotorGrafico: EditText
    private lateinit var btnActualizar: Button
    private lateinit var btnMostrarDetalles: Button
    private lateinit var btnGuardar: Button

    private lateinit var fechaSeleccionada: Calendar // Para almacenar la fecha seleccionada
    private var currentVideojuegoId: Int? = null // ID del videojuego actual

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.menu2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de los elementos de la interfaz
        etPlataforma = findViewById(R.id.etPlataforma)
        etPublicador = findViewById(R.id.etPublicador)
        cbModoHistoria = findViewById(R.id.cbModoHistoria)
        etFechaLanzamiento = findViewById(R.id.etFechaLanzamiento)
        etNumeroJugadores = findViewById(R.id.etNumeroJugadores)
        etDuracionPromedio = findViewById(R.id.etDuracionPromedio)
        etMotorGrafico = findViewById(R.id.etMotorGrafico)
        btnActualizar = findViewById(R.id.btnActualizar)
        btnMostrarDetalles = findViewById(R.id.btnMostrarDetalles)
        btnGuardar = findViewById(R.id.btnGuardar)

        // Establecer la fecha seleccionada como una instancia del calendario
        fechaSeleccionada = Calendar.getInstance()

        // Obtener el ID del videojuego actual desde el Intent
        currentVideojuegoId = intent.getIntExtra("VIDEOJUEGO_ID", -1).takeIf { it != -1 }

        // Cargar los detalles del videojuego si se está editando
        cargarVideojuego(currentVideojuegoId)

        // Configuración de los botones
        btnActualizar.setOnClickListener {
            actualizarVideojuego()
        }

        btnMostrarDetalles.setOnClickListener {
            mostrarDetalles()
        }

        btnGuardar.setOnClickListener {
            actualizarVideojuego()
            volverAlPrimerLayout()
        }

        // Configurar el listener para el campo de fecha
        etFechaLanzamiento.setOnClickListener {
            mostrarDatePicker()
        }
    }

    private fun cargarVideojuego(id: Int?) {
        id?.let {
            // Cargar detalles del videojuego
            val videojuego = VideojuegoManager.getInstance().obtenerVideojuegoPorId(id)
            videojuego?.let { v ->
                etPlataforma.setText(v.plataforma)
                etPublicador.setText(v.publicador)
                cbModoHistoria.isChecked = v.modoHistoria
                etFechaLanzamiento.setText(dateToString(v.fechaLanzamiento)) // Asegúrate de que v.fechaLanzamiento sea un Calendar o String
                etNumeroJugadores.setText(v.numeroJugadores.toString())
                etDuracionPromedio.setText(v.duracionPromedio.toString())
                etMotorGrafico.setText(v.motorGrafico)
            }
        }
    }

    private fun dateToString(calendar: Calendar): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    private fun mostrarDatePicker() {
        val dia = fechaSeleccionada.get(Calendar.DAY_OF_MONTH)
        val mes = fechaSeleccionada.get(Calendar.MONTH)
        val anio = fechaSeleccionada.get(Calendar.YEAR)

        // Crear el DatePickerDialog
        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            // Actualizar el calendario con la fecha seleccionada
            fechaSeleccionada.set(selectedYear, selectedMonth, selectedDay)
            // Formatear la fecha en el EditText
            etFechaLanzamiento.setText(dateToString(fechaSeleccionada))
        }, anio, mes, dia)

        datePickerDialog.show()
    }

    private fun actualizarVideojuego() {
        // Obtener los valores de los EditText y CheckBox
        val plataforma = etPlataforma.text.toString()
        val publicador = etPublicador.text.toString()
        val modoHistoria = cbModoHistoria.isChecked
        val numeroJugadores = etNumeroJugadores.text.toString().toIntOrNull() ?: 1
        val duracionPromedio = etDuracionPromedio.text.toString().toIntOrNull() ?: 15
        val motorGrafico = etMotorGrafico.text.toString()

        // Obtener la fecha de lanzamiento como Calendar
        val fechaLanzamiento = fechaSeleccionada

        // Actualizar el videojuego en el VideojuegoManager
        currentVideojuegoId?.let { id ->
            VideojuegoManager.getInstance().editarVideojuego(
                id,                    // ID del videojuego
                null,                  // Titulo
                null,                  // Genero
                plataforma,            // Plataforma
                null,                  // Desarrollador
                publicador,            // Publicador
                null,                  // Es multijugador
                modoHistoria,          // Modo historia
                null,                  // Precio
                null,                  // Calificación
                null,                  // Clasificación de edad
                fechaLanzamiento,      // Fecha de lanzamiento
                numeroJugadores,       // Número de jugadores
                duracionPromedio,      // Duración promedio
                motorGrafico           // Motor gráfico
            )

            Toast.makeText(this, "Videojuego actualizado correctamente.", Toast.LENGTH_SHORT).show()
        } ?: run {
            Toast.makeText(this, "No se encontró un videojuego para actualizar.", Toast.LENGTH_SHORT).show()
        }
    }



    private fun mostrarDetalles() {
        currentVideojuegoId?.let { id ->
            val detalles = VideojuegoManager.getInstance().mostrarDetalles(id)
            Log.i("DetallesVideojuego", detalles) // Registro en logcat
            Toast.makeText(this, detalles, Toast.LENGTH_LONG).show()
        } ?: run {
            Toast.makeText(this, "No se encontró un videojuego para mostrar detalles.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun volverAlPrimerLayout() {
        Toast.makeText(this, "Volviendo al primer layout...", Toast.LENGTH_SHORT).show()

        // Intent para iniciar la actividad del primer layout
        val intent = Intent(this, MainActivity::class.java) // Reemplaza con el nombre de tu actividad
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

        // Finaliza la actividad actual si es necesario
        finish()
    }
}
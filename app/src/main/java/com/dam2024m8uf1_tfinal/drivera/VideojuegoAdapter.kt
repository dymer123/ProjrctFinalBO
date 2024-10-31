package com.dam2024m8uf1_tfinal.drivera

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dam2024m8uf1_tfinal.drivera.Singletoon.Videojuego

class VideojuegoAdapter (
    private val videojuegos: List<Videojuego>,
    private val onEditClick: (Videojuego) -> Unit,
    private val onDeleteClick: (Videojuego) -> Unit
) : RecyclerView.Adapter<VideojuegoAdapter.VideojuegoViewHolder>() {

    class VideojuegoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvPlataforma: TextView = itemView.findViewById(R.id.tvPlataforma)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideojuegoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_videojuego, parent, false)
        return VideojuegoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideojuegoViewHolder, position: Int) {
        val videojuego = videojuegos[position]
        holder.tvTitulo.text = videojuego.titulo // Suponiendo que `titulo` es una propiedad de Videojuego
        holder.tvPlataforma.text = videojuego.plataforma // Suponiendo que `plataforma` es una propiedad de Videojuego

        // Manejar clic en editar
        holder.btnEdit.setOnClickListener {
            onEditClick(videojuego)
        }

        // Manejar clic en eliminar
        holder.btnDelete.setOnClickListener {
            onDeleteClick(videojuego)
        }
    }

    override fun getItemCount(): Int = videojuegos.size
}

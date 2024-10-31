package com.dam2024m8uf1_tfinal.drivera

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dam2024m8uf1_tfinal.drivera.Singletoon.Videojuego

class VideojuegoAdapter(
    private val videojuegos: List<Videojuego>,
    private val onEditClick: (Videojuego) -> Unit,
    private val onDeleteClick: (Videojuego) -> Unit
) : RecyclerView.Adapter<VideojuegoAdapter.VideojuegoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideojuegoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_videojuego, parent, false)
        return VideojuegoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideojuegoViewHolder, position: Int) {
        val videojuego = videojuegos[position]
        holder.bind(videojuego)
    }

    override fun getItemCount(): Int = videojuegos.size

    inner class VideojuegoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        private val tvPlataforma: TextView = itemView.findViewById(R.id.tvPlataforma)
        private val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        private val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

        fun bind(videojuego: Videojuego) {
            tvTitulo.text = videojuego.titulo
            tvPlataforma.text = videojuego.plataforma

            btnEdit.setOnClickListener { onEditClick(videojuego) }
            btnDelete.setOnClickListener { onDeleteClick(videojuego) }
        }
    }
}

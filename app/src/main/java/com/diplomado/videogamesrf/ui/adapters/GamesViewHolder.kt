package com.diplomado.videogamesrf.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diplomado.videogamesrf.data.remote.model.GameDto
import com.diplomado.videogamesrf.databinding.GameElementBinding

/**
 * ViewHolder encargado de representar un solo elemento de la lista de juegos.
 * @param binding ViewBinding generado para el layout game_element.xml
 */
class GamesViewHolder(
    private val binding: GameElementBinding
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Asocia los datos de un GameDto con los elementos visuales del layout.
     */
    fun bind(game: GameDto) {
        // Asigna el título del juego al TextView
        binding.tvTitle.text = game.title

        // Carga la imagen del thumbnail usando Glide
        Glide.with(binding.root)
            .load(game.thumbnail)
            .into(binding.ivThumbnail)
    }
}
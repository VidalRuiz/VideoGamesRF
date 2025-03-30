package com.diplomado.videogamesrf.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diplomado.videogamesrf.data.remote.model.GameDto
import com.diplomado.videogamesrf.databinding.GameElementBinding

/**
 * Adaptador para el RecyclerView que muestra una lista de juegos.
 * @param games Lista de juegos a mostrar.
 * @param onGameClicked Callback que se ejecuta cuando se hace clic en un juego.
 */
class GamesAdapter(
    private val games: List<GameDto>,
    private val onGameClicked: (GameDto) -> Unit
) : RecyclerView.Adapter<GamesViewHolder>() {

    /**
     * Crea el ViewHolder inflando el layout correspondiente.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val binding = GameElementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GamesViewHolder(binding)
    }

    /**
     * Retorna el número total de elementos en la lista.
     */
    override fun getItemCount(): Int = games.size

    /**
     * Asocia los datos con la vista en la posición especificada.
     */
    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game) // Vincula los datos con la vista

        // Maneja el clic en el elemento y dispara el callback
        holder.itemView.setOnClickListener {
            onGameClicked(game)
        }
    }
}
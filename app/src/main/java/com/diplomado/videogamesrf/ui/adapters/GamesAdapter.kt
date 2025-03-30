package com.diplomado.videogamesrf.ui.adapters

import android.view.ViewGroup
import android.view.LayoutInflater

import androidx.recyclerview.widget.RecyclerView
import com.diplomado.videogamesrf.data.remote.model.GameDto
import com.diplomado.videogamesrf.databinding.GameElementBinding

class GamesAdapter (
    private val games: List<GameDto>,
    private val onGameClicked: (GameDto) -> Unit
) : RecyclerView.Adapter<GamesViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val binding = GameElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return games.count()
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)
        holder.itemView.setOnClickListener {
            onGameClicked(game)
        }

    }

}
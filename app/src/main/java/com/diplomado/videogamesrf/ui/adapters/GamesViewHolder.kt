package com.diplomado.videogamesrf.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diplomado.videogamesrf.data.remote.model.GameDto
import com.diplomado.videogamesrf.databinding.GameElementBinding

class GamesViewHolder (private val binding: GameElementBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(game: GameDto)
    {
        binding.tvTitle.text = game.title
        //Picasso.get().load(game.thumbnail).into(binding.ivThumbnail)

        Glide.with(binding.root).load(game.thumbnail).into(binding.ivThumbnail)
    }
}
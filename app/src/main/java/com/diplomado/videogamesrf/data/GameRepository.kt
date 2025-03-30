package com.diplomado.videogamesrf.data

import com.diplomado.videogamesrf.data.remote.GamesApi
import com.diplomado.videogamesrf.data.remote.RetrofitHelper
import com.diplomado.videogamesrf.data.remote.model.GameDetailDto
import com.diplomado.videogamesrf.data.remote.model.GameDto
import retrofit2.Retrofit
class GameRepository (
    private val retrofit: Retrofit
)
{
    private val gamesApi: GamesApi = retrofit.create(GamesApi::class.java)

    suspend fun getGames(url: String) : List<GameDto> = gamesApi.getGames(url)
    suspend fun getGames() : List<GameDto> = gamesApi.getGames()
    suspend fun getGameDetail(id: String?) : GameDetailDto = gamesApi.getGameDetail(id)
    suspend fun getGamesApiary() : List<GameDto> = gamesApi.getGamesApiary()
    suspend fun getGameDetailApiary(id: String?) : GameDetailDto = gamesApi.getGameDetailApiary(id)

}
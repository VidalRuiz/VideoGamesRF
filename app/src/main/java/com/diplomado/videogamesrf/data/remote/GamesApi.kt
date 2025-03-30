package com.diplomado.videogamesrf.data.remote

import com.diplomado.videogamesrf.data.remote.model.GameDetailDto
import com.diplomado.videogamesrf.data.remote.model.GameDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

//https://www.serverbpw.com/cm/games/games_list.php
//https://www.serverbpw.com/cm/games/game_detail.php?id=21357


// Analogo a Dao en Room
interface GamesApi {
    @GET("cm/games/games_list.php")
    suspend fun getGames() : List<GameDto>

    @GET
    suspend fun getGames(@Url url: String?): List<GameDto>

    @GET("cm/games/game_detail.php")
    suspend fun getGameDetail(@Query("id") id: String?): GameDetailDto

    //Apiary
    //https://private-a649a-games28.apiary-mock.com/games/games_list
    //https://private-a649a-games28.apiary-mock.com/games/game_detail/21357
    @GET("games/games_list")
    suspend fun getGamesApiary() : List<GameDto>
    @GET("games/game_detail/{id}}")
    suspend fun getGameDetailApiary(@Path("id") id: String?): GameDetailDto
}
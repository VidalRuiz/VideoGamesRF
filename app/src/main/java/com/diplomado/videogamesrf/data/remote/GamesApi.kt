package com.diplomado.videogamesrf.data.remote

import com.diplomado.videogamesrf.data.remote.model.GameDetailDto
import com.diplomado.videogamesrf.data.remote.model.GameDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Interfaz de acceso remoto (Retrofit) para consumir la API de videojuegos.
 * Similar a un DAO en Room.
 */
interface GamesApi {

    /**
     * Obtiene la lista de juegos desde el servidor principal.
     * URL: https://www.serverbpw.com/cm/games/games_list.php
     */
    @GET("cm/games/games_list.php")
    suspend fun getGames(): List<GameDto>

    /**
     * Alternativa dinámica para obtener juegos desde una URL completa.
     * Útil para pruebas o URLs diferentes.
     */
    @GET
    suspend fun getGames(@Url url: String?): List<GameDto>

    /**
     * Obtiene el detalle de un juego desde el servidor principal usando el ID del juego.
     * URL base: https://www.serverbpw.com/cm/games/game_detail.php?id={id}
     */
    @GET("cm/games/game_detail.php")
    suspend fun getGameDetail(@Query("id") id: String?): GameDetailDto

    /**
     * Obtiene la lista de juegos desde el servidor mock de Apiary.
     * URL: https://private-a649a-games28.apiary-mock.com/games/games_list
     */
    @GET("games/games_list")
    suspend fun getGamesApiary(): List<GameDto>

    /**
     * Obtiene el detalle de un juego desde Apiary usando path param.
     * URL: https://private-a649a-games28.apiary-mock.com/games/game_detail/{id}
     */
    @GET("games/game_detail/{id}")
    suspend fun getGameDetailApiary(@Path("id") id: String?): GameDetailDto
}
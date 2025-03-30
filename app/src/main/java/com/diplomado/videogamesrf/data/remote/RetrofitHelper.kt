package com.diplomado.videogamesrf.data.remote

import com.diplomado.videogamesrf.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Clase auxiliar para configurar y proporcionar una instancia de Retrofit.
 * Se utiliza para realizar peticiones HTTP a la API remota.
 */
class RetrofitHelper {

    // Interceptor para registrar el detalle de las peticiones y respuestas HTTP (ideal para debugging)
    private val interceptor = HttpLoggingInterceptor().apply {
        // Nivel BODY muestra encabezados, cuerpo de solicitud/respuesta y metadatos (solo usar en desarrollo)
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Cliente HTTP personalizado con el interceptor añadido
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    /**
     * Crea y retorna una instancia de Retrofit ya configurada con:
     * - Base URL (definida en Constants)
     * - Cliente HTTP con interceptor
     * - Conversor Gson para parsear JSON
     */
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
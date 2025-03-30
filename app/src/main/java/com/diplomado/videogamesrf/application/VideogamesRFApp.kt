package com.diplomado.videogamesrf.application

import android.app.Application
import com.diplomado.videogamesrf.data.GameRepository
import com.diplomado.videogamesrf.data.remote.RetrofitHelper

class VideogamesRFApp: Application() {

    private val retrofit by lazy{
        RetrofitHelper().getRetrofit()
    }

    val repository by lazy {
        GameRepository(retrofit)
    }

}
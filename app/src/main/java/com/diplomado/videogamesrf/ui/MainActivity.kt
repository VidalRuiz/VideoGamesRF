package com.diplomado.videogamesrf.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.diplomado.videogamesrf.databinding.ActivityMainBinding
import com.diplomado.videogamesrf.ui.fragments.GameListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null)
        {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, GameListFragment())
                .commit()
        }
    }

    /*
    private lateinit var repository : GameRepository
    private lateinit var retrofit: Retrofit
    fun Prueba()
    {
        retrofit = RetrofitHelper().getRetrofit()
        repository = GameRepository(retrofit)
        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
        Log.d(Constants.LOGTAG, "onCreate")

        lifecycleScope.launch{
            try
            {
                val games = repository.getGames()
                games.forEach { game ->
                    Log.d(Constants.LOGTAG, "Juego: ${game.title}")
                }
                Toast.makeText(this@MainActivity, "Bienvenido", Toast.LENGTH_SHORT).show()
            }
            catch (exception: Exception)
            {
                exception.printStackTrace()
                Log.e(Constants.LOGTAG, "Error: ${exception.message}")
            }
        }
    }
     */
}
package com.diplomado.videogamesrf.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.diplomado.videogamesrf.R
import com.diplomado.videogamesrf.application.VideogamesRFApp
import com.diplomado.videogamesrf.data.GameRepository
import com.diplomado.videogamesrf.data.remote.model.GameDto
import com.diplomado.videogamesrf.databinding.FragmentGameListBinding
import com.diplomado.videogamesrf.ui.adapters.GamesAdapter
import com.diplomado.videogamesrf.utils.Constants
import kotlinx.coroutines.launch

class GameListFragment : Fragment() {

    private var _binding: FragmentGameListBinding? = null
    private val binding get() = _binding!!
    private lateinit var repository: GameRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRepository()
        fetchGames()
    }

    /**
     * Inicializa el repositorio desde la aplicación
     */
    private fun initRepository() {
        repository = (requireActivity().application as VideogamesRFApp).repository
    }

    /**
     * Obtiene la lista de juegos y configura la vista del RecyclerView
     */
    private fun fetchGames() {
        lifecycleScope.launch {
            try {
                val games = repository.getGames()
                setupRecyclerView(games)
            } catch (e: Exception) {
                handleError(e)
            } finally {
                hideLoading()
            }
        }
    }

    /**
     * Configura el RecyclerView con la lista de juegos y su listener de clics
     */
    private fun setupRecyclerView(games: List<GameDto>) {
        binding.rvGames.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = GamesAdapter(games) { selectedGame ->
                onGameSelected(selectedGame.id)
            }
        }
    }

    /**
     * Maneja el evento de selección de un juego
     */
    private fun onGameSelected(gameId: String?) {
        gameId?.let { id ->
            Log.d(Constants.LOGTAG, "Click en el juego $id")
            navigateToGameDetail(id)
        }
    }

    /**
     * Navega al fragmento de detalle del juego
     */
    private fun navigateToGameDetail(gameId: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GameDetailFragment.newInstance(gameId))
            .addToBackStack(null)
            .commit()
    }

    /**
     * Muestra un mensaje de error si algo falla
     */
    private fun handleError(e: Exception) {
        e.printStackTrace()
        Toast.makeText(requireContext(), "Error en la conexión", Toast.LENGTH_SHORT).show()
    }

    /**
     * Oculta el ProgressBar de carga
     */
    private fun hideLoading() {
        binding.pbLoading.visibility = View.GONE
    }
}
package com.diplomado.videogamesrf.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.diplomado.videogamesrf.R
import com.diplomado.videogamesrf.application.VideogamesRFApp
import com.diplomado.videogamesrf.data.GameRepository
import com.diplomado.videogamesrf.databinding.FragmentGameDetailBinding
import kotlinx.coroutines.launch

private const val ARG_GAMEID = "id"

class GameDetailFragment : Fragment() {

    private var _binding: FragmentGameDetailBinding? = null
    private val binding get() = _binding!!

    private var gameId: String? = null
    private lateinit var repository: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrieveArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRepository()
        loadGameDetails()
    }

    /**
     * Libera la referencia del binding para evitar memory leaks
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     * Recupera el argumento enviado al fragmento (el ID del juego)
     */
    private fun retrieveArguments() {
        arguments?.let { args ->
            gameId = args.getString(ARG_GAMEID)
        }
    }

    /**
     * Inicializa el repositorio a partir del Application
     */
    private fun initRepository() {
        repository = (requireActivity().application as VideogamesRFApp).repository
    }

    /**
     * Llama al repositorio para obtener los detalles del juego
     */
    private fun loadGameDetails() {
        lifecycleScope.launch {
            try {
                val gameDetail = repository.getGameDetail(gameId)
                displayGameDetails(gameDetail.title, gameDetail.image, gameDetail.longDesc)
            } catch (e: Exception) {
                showError()
            } finally {
                hideLoading()
            }
        }
    }

    /**
     * Muestra los detalles del juego en pantalla
     */
    private fun displayGameDetails(title: String?, imageUrl: String?, description: String?) {
        binding.apply {
            tvTitle.text = title
            tvLongDesc.text = description

            Glide.with(requireActivity())
                .load(imageUrl)
                .into(ivImage)
        }
    }

    /**
     * Muestra un mensaje de error en caso de fallo
     */
    private fun showError() {
        // Aquí podrías agregar un Toast o mostrar un mensaje en pantalla
        // Toast.makeText(requireContext(), "Error al cargar el juego", Toast.LENGTH_SHORT).show()
    }

    /**
     * Oculta el ProgressBar
     */
    private fun hideLoading() {
        binding.pbLoading.visibility = View.GONE
    }

    companion object {
        /**
         * Crea una instancia del fragment con el ID del juego como argumento
         */
        @JvmStatic
        fun newInstance(id: String) =
            GameDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_GAMEID, id)
                }
            }
    }
}
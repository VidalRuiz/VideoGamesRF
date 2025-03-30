package com.diplomado.videogamesrf.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.diplomado.videogamesrf.R
import com.diplomado.videogamesrf.application.VideogamesRFApp
import com.diplomado.videogamesrf.data.GameRepository
import com.diplomado.videogamesrf.databinding.FragmentGameListBinding
import com.diplomado.videogamesrf.ui.adapters.GamesAdapter
import com.diplomado.videogamesrf.utils.Constants
import kotlinx.coroutines.launch

class GameListFragment : Fragment() {

    private var _binding: FragmentGameListBinding? = null
    private val binding get() = _binding!!
    private lateinit var repository : GameRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        _binding = FragmentGameListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        repository = (requireActivity().application as VideogamesRFApp).repository
        lifecycleScope.launch {
            try
            {
                val games = repository.getGames()
                binding.rvGames.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = GamesAdapter(games) { game ->
                        Log.d(Constants.LOGTAG, "Juego: ${game.title}")

                        game.id?.let { id ->
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(
                                    R.id.fragment_container,
                                    GameDetailFragment.newInstance(id)
                                )
                                .addToBackStack(null)
                                .commit()
                        }
                    }
                }
            }
            catch (exception: Exception)
            {
                exception.printStackTrace()
            }
            finally {
                binding.pbLoading.visibility = View.GONE

            }
        }
    }


}
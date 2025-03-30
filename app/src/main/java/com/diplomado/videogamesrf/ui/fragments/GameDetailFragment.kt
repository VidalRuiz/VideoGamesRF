package com.diplomado.videogamesrf.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.diplomado.videogamesrf.R
import com.diplomado.videogamesrf.databinding.FragmentGameDetailBinding

private const val ARG_GAMEID = "id"

class GameDetailFragment : Fragment() {

    private var _binding: FragmentGameDetailBinding? = null
    private val binding get() = _binding!!


    private var gameId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {args ->
            gameId = args.getString(ARG_GAMEID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameDetailBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String) =
            GameDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_GAMEID, id)
                }
            }
    }
}
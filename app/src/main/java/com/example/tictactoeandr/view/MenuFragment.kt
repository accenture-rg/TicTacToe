package com.example.tictactoeandr.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tictactoeandr.R
import com.example.tictactoeandr.databinding.FragmentMenuBinding
import com.example.tictactoeandr.data.Player


class MenuFragment : Fragment() {
    private lateinit var binding : FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root

        var player1 = arguments?.getSerializable("player1") as? Player
        var player2 = arguments?.getSerializable("player2") as? Player



        binding.buttonNewGame.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_boardFragment)
        }

        binding.buttonHistory.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_historyFragment)
        }

        binding.buttonAbout.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_aboutFragment)
        }

        return view
    }
}
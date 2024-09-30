package com.example.tictactoeandr.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tictactoeandr.R
import com.example.tictactoeandr.databinding.FragmentPlayersNamesBinding
import com.example.tictactoeandr.data.Player
import com.example.tictactoeandr.viewmodel.GameViewModel

class PlayersNamesFragment : Fragment() {
    private lateinit var binding : FragmentPlayersNamesBinding
    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayersNamesBinding.inflate(inflater, container, false)

        binding.acceptButton.setOnClickListener {
            val player1name = binding.player1nameEditText.text.toString()
            val player2name = binding.player2nameEditText.text.toString()

            if(player1name.isEmpty() || player2name.isEmpty()) {
                Toast.makeText(activity, "Enter player's names to continue.", Toast.LENGTH_LONG).show()
            } else {
                viewModel.setUpGame(player1name, player2name)
                findNavController().navigate(R.id.action_playersNamesFragment_to_menuFragment)
            }
        }

        return binding.root
    }


}
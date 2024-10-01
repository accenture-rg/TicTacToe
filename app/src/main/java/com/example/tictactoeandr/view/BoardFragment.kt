package com.example.tictactoeandr.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tictactoeandr.R
import com.example.tictactoeandr.databinding.FragmentBoardBinding
import com.example.tictactoeandr.viewmodel.GameViewModel

class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding
    private lateinit var adapter: BoardAdapter
    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = BoardAdapter{index -> viewModel.makeMove(index)}
        binding = FragmentBoardBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = adapter
        viewModel.currentPlayer.observe(viewLifecycleOwner) { player ->
            binding.turnInfoTextView.text = "It\'s ${player?.name}\'s turn!"
        }

        viewModel.boardState.observe(viewLifecycleOwner) { board ->
            adapter.updateBoard(board)
        }

        viewModel.players.observe(viewLifecycleOwner) {playersList ->
            binding.scoreTextView.text = "${playersList[0].name} ${playersList[0].wonGames} - ${playersList[1].name} ${playersList[1].wonGames}"
        }

        viewModel.winner.observe(viewLifecycleOwner) {winner ->
            if(winner != null) {
                binding.turnInfoTextView.text = "${winner.name} won. Congratulations!"
            }
        }

        binding.restartGameButton.setOnClickListener {
            viewModel.resetGame()
        }

        return binding.root
    }
}
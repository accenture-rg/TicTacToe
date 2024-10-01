package com.example.tictactoeandr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tictactoeandr.R
import com.example.tictactoeandr.databinding.FragmentBoardBinding
import com.example.tictactoeandr.databinding.FragmentHistoryBinding
import com.example.tictactoeandr.viewmodel.GameViewModel

class HistoryFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyAdapter = HistoryAdapter()
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.historyRecyclerView.adapter = historyAdapter

        viewModel.pastGames.observe(viewLifecycleOwner) {gamesList ->
            historyAdapter.updateGameHistory(gamesList)
        }

        return binding.root
    }

}
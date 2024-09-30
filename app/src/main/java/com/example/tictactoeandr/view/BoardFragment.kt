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
        //adapter = BoardAdapter(MutableList(9) {' '}, viewModel::makeMove)
        adapter = BoardAdapter{index -> viewModel.makeMove(index)}
        binding = FragmentBoardBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = adapter
        viewModel.currentPlayer.observe(viewLifecycleOwner) { player ->
            binding.turnInfoTextView.text = "It\'s ${player.name}\'s turn!"
        }

        viewModel.boardState.observe(viewLifecycleOwner) { board ->
            adapter.updateBoard(board)
        }

        viewModel.players.observe(viewLifecycleOwner) {playersList ->
            binding.scoreTextView.text = "${playersList[0].name} ${playersList[0].wonGames} - ${playersList[1].name} ${playersList[1].wonGames}"
        }

        viewModel.winner.observe(viewLifecycleOwner) {winner ->
            binding.turnInfoTextView.text = "${winner?.name} won. Congratulations!"

        }

        binding.restartGameButton.setOnClickListener {
            viewModel.resetGame()
        }

        return binding.root
    }


    /*fun startGame(player1: Player, player2: Player): Boolean {
        var boardStateArray = Array(9) {' '}
        val playersList = arrayListOf<Player>(player1, player2)
        var moveCounter = 0

        while(checkIfGameIsWon(moveCounter, boardStateArray) == null) {
            for(player: Player in playersList) {
                println("${player.name}'s turn, choose empty field of the board!")
                displayBoard(boardStateArray)
                var chosenField: Char
                var moveDone = false
                while (!moveDone) {
                    chosenField = readln().single()
                    if (chosenField.equals('m')) {
                        if(displayMenu(true, player1, player2)) {
                            return true
                        }
                    } else if (!hintArray.any {it == chosenField}) {
                        println("Invalid input! Please choose integer from 1 to 9.")
                    } else {
                        if (!boardStateArray[chosenField.digitToInt() - 1].equals(' ')) {
                            println("This field is already marked. Choose empty field!")
                        } else {
                            boardStateArray[chosenField.digitToInt() - 1] = player.mark
                            moveCounter++
                            moveDone = true
                        }
                    }
                }

                if(moveCounter == 9 && checkIfGameIsWon(moveCounter, boardStateArray) == null) {
                    println("Nobody wins!")
                    moveCounter = 0
                    break
                }

                var winnerList = playersList.filter {it.mark == checkIfGameIsWon(moveCounter, boardStateArray) }
                if(winnerList.size == 1) {
                    var winner = winnerList.first()
                    println("Game over. ${winner.name} wins!")
                    winner.wonGames++
                    //break
                }
            }
            displayMenu(false, player1, player2)
        }
        *//*var winnerList = playersList.filter {it.mark == checkIfGameIsWon(moveCounter, boardStateArray) }
        var winner = winnerList.first()
        println("Game over. ${winner.name} wins!")*//*
        return true
    }*/






}
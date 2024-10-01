package com.example.tictactoeandr.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictactoeandr.data.Game
import com.example.tictactoeandr.data.PastGame
import com.example.tictactoeandr.data.Player

class GameViewModel : ViewModel() {

    private val _players = MutableLiveData<MutableList<Player>>()
    val players: LiveData<MutableList<Player>> get() = _players

    private val _currentPlayer = MutableLiveData<Player>()
    val currentPlayer: LiveData<Player> get() = _currentPlayer

    private val _winner = MutableLiveData<Player?>()
    val winner: LiveData<Player?> get() = _winner

    private val _pastGames = MutableLiveData<MutableList<PastGame>>(mutableListOf())
    val pastGames: LiveData<MutableList<PastGame>> get() = _pastGames

    private val _boardState = MutableLiveData<MutableList<Char>>()
    val boardState: LiveData<MutableList<Char>> get() = _boardState


    //private var game: Game = Game()

    fun setUpGame(player1name: String, player2name: String) {
        val player1 = Player('x', player1name)
        val player2 = Player('o', player2name)
        _players.value = mutableListOf(player1, player2)
        _currentPlayer.value = player1
        _boardState.value = MutableList<Char>(9) {' '}
    }

    fun makeMove(index: Int): Boolean {
        val currentBoardState = _boardState.value?.toMutableList() ?: return false
        val listOfPlayers = _players.value
        if (currentBoardState[index] != ' ' || _winner.value != null || _currentPlayer.value == null) {
            return false
        }
        currentBoardState[index] = _currentPlayer.value?.mark!!
        _boardState.value = currentBoardState
        if (listOfPlayers != null) {
            if (checkIfGameIsWon(currentBoardState) == null) {
                if (_currentPlayer.value == listOfPlayers.get(0)) {
                    _currentPlayer.value = listOfPlayers.get(1)
                } else {
                    _currentPlayer.value = listOfPlayers.get(0)
                }
            } else {
                for (player: Player in listOfPlayers) {
                    if(player.mark == checkIfGameIsWon(currentBoardState)) {
                        _winner.value = player
                        player.wonGames++
                    }
                }
                _players.value = listOfPlayers ?: mutableListOf<Player>()
                saveGame()
            }
        }
        //TODO: Saving game that has no winner
        /*if (checkIfGameIsWon(currentBoardState) == null && !currentBoardState.contains(' ')){

        }*/
        return true
    }

    fun checkIfGameIsWon(list: MutableList<Char>): Char? {

        val marks = "xo"
        for (mark: Char in marks) {
            //horizontal
            for (i in 0..2) {
                var index = i * 3
                if (list[index] == mark && list[index + 1] == mark && list[index + 2] == mark)
                    return mark
            }

            //vertical
            for (i in 0..2) {
                if (list[i] == mark && list[i + 3] == mark && list[i + 6] == mark)
                    return mark
            }

            //cross
            if ((list[0] == mark && list[4] == mark && list[8] == mark) || (list[2] == mark && list[4] == mark && list[6] == mark))
                return mark
        }
        return null
    }

    fun resetGame() {
        _boardState.value = MutableList(9) { ' ' }
        if (_winner.value != null ) _currentPlayer.value = _winner.value
        _winner.value = null

    }

    fun saveGame() {
        var pastGame = PastGame(_players.value, _winner.value)
        _pastGames.value?.add(pastGame)
    }
}
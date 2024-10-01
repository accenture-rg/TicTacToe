package com.example.tictactoeandr.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictactoeandr.data.Game
import com.example.tictactoeandr.data.PastGame
import com.example.tictactoeandr.data.Player
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

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

    private var game: Game = Game()

    private lateinit var disposable: Disposable

    init {

    }

    fun setUpGame(player1name: String, player2name: String) {
        game.setUpGame(player1name, player2name)
        _players.value = game.playersList
        _currentPlayer.value = game.playersList[0]
        _boardState.value = game.boardList
    }

    fun makeMove(index: Int) {
        disposable = game.makeMove(index).observeOn(Schedulers.computation()).subscribe(
            { successValue -> if(successValue) {
                _boardState.postValue(game.boardList)
                _currentPlayer.postValue(game.currentPlayer)
                _players.postValue(game.playersList)
                _winner.postValue(game.winner)
                _pastGames.postValue(game.pastGamesList)
            } },
            { errorValue -> }
        )

    }

    fun resetGame() {
        game.resetGame()
        _boardState.value = game.boardList
        _currentPlayer.value = game.currentPlayer
        _winner.value = game.winner
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
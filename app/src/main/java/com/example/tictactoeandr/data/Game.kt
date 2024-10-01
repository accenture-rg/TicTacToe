package com.example.tictactoeandr.data

import io.reactivex.rxjava3.core.Single

class Game {

    var boardList: MutableList<Char> = MutableList(9) {' '}
    var winner: Player? = null
    lateinit var currentPlayer: Player
    lateinit var playersList: MutableList<Player>
    var pastGamesList: MutableList<PastGame> = mutableListOf<PastGame>()

    fun setUpGame(player1name: String, player2name: String) {
        val player1 = Player('x', player1name)
        val player2 = Player('o', player2name)
        playersList = mutableListOf(player1, player2)
        currentPlayer = player1
    }

    fun makeMove(index: Int): Single<Boolean> {
        if (boardList[index] != ' ' || winner != null || currentPlayer == null) {
            return Single.just(false)
        }
        boardList[index] = currentPlayer.mark

        if (playersList != null) {
            if (checkIfGameIsWon(boardList) == null) {
                if (currentPlayer == playersList[0]) {
                    currentPlayer = playersList[1]
                } else {
                    currentPlayer = playersList[0]
                }
            } else {
                for (player: Player in playersList) {
                    if(player.mark == checkIfGameIsWon(boardList)) {
                        winner = player
                        player.wonGames++
                    }
                }
                saveGame()
            }
        }
        return Single.just(true)
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

            //diagonal
            if ((list[0] == mark && list[4] == mark && list[8] == mark) || (list[2] == mark && list[4] == mark && list[6] == mark))
                return mark
        }
        return null
    }

    fun resetGame() {
        boardList = MutableList(9) { ' ' }
        if (winner != null ) currentPlayer = winner as Player
        winner = null
    }

    fun saveGame() {
        var pastGame = PastGame(playersList, winner)
        pastGamesList.add(pastGame)
    }


}
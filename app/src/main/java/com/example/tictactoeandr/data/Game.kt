package com.example.tictactoeandr.data

import io.reactivex.Single

class Game {

    var board: Array<Char> = Array<Char>(9) { ' ' }

    fun makeMove(index: Int, array: Array<Char>): Single<Array<Char>> {
        //var newArray: Array<Char> =
        if (checkIfGameIsWon(array) == null) {

        }
        return Single.just(array)
    }

    fun checkIfGameIsWon(array: Array<Char>): Char? {

        val marks = "xo"
        for (mark: Char in marks) {
            for (i in array.indices step 2) {
                if (array[i] == mark && array[i + 1] == mark && array[i + 2] == mark)
                    return mark
            }

            for (i in 0..2) {
                if (array[i] == mark && array[i + 3] == mark && array[i + 6] == mark)
                    return mark

            }

            if ((array[0] == mark && array[4] == mark && array[8] == mark) || (array[2] == mark && array[4] == mark && array[6] == mark))
                return mark
        }
        return null
    }
}
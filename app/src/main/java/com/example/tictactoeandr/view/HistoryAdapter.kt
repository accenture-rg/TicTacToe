package com.example.tictactoeandr.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoeandr.data.PastGame
import com.example.tictactoeandr.databinding.PastGameItemBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var gameHistoryList: List<PastGame> = listOf()

    inner class HistoryViewHolder(var binding: PastGameItemBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(pastGame: PastGame) {
            val player1name = pastGame.players?.get(0)?.name
            val player2name = pastGame.players?.get(1)?.name
            val winnerName = pastGame.winner?.name
            binding.playersTextView.text = "$player1name, $player2name"
            binding.winnerTextView.text = "$winnerName"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.HistoryViewHolder {
        val binding = PastGameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.HistoryViewHolder, position: Int) {
        holder.bind(gameHistoryList[position])
    }

    override fun getItemCount(): Int = gameHistoryList.size

    fun updateGameHistory(newHistoryList: List<PastGame>) {
        gameHistoryList = newHistoryList
        notifyDataSetChanged()
    }
}
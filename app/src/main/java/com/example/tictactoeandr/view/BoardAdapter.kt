package com.example.tictactoeandr.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoeandr.R
import com.example.tictactoeandr.databinding.BoardFieldBinding


class BoardAdapter(private val onClick: (Int) -> Unit): RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {
//class BoardAdapter(private var board: List<Char>,var onClick: (Int) -> Unit): RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    private var board: List<Char> = List(9) {' '}

    inner class BoardViewHolder(var binding: BoardFieldBinding, var onClick: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        fun bind(index: Int) {
            val mark = board[index]
            when(mark) {
                'x' -> binding.fieldImageButton.setImageResource(R.drawable.x)
                'o' -> binding.fieldImageButton.setImageResource(R.drawable.o)
                else -> binding.fieldImageButton.setImageResource(android.R.color.transparent)
            }

            binding.fieldImageButton.setOnClickListener {
                onClick(index)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val binding = BoardFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int = 9

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.bind(position)
    }

    fun updateBoard(newBoard: List<Char>) {
        board = newBoard
        notifyDataSetChanged()
    }
}
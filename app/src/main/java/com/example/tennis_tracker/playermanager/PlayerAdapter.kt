package com.example.tennis_tracker.playermanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tennis_tracker.database.Player
import com.example.tennis_tracker.databinding.RecyclerviewItemBinding

class PlayerAdapter : ListAdapter<Player, PlayerAdapter.ViewHolder>(PlayerDiffCallback()) {
    class ViewHolder private constructor(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Player) {
            binding.nameVal.text = item.playerId.toString() + item.playerName
            binding.yearVal.text = item.playerYear.toString()
            binding.rankVal.text = item.playerRank.toString()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater =
                    LayoutInflater.from(parent.context)
                val binding = RecyclerviewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}

class PlayerDiffCallback : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.playerId == newItem.playerId
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem == newItem
    }
}


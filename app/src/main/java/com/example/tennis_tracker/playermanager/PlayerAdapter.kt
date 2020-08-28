package com.example.tennis_tracker.playermanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tennis_tracker.R
import com.example.tennis_tracker.database.Player

class PlayerAdapter: RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val playerName: TextView = itemView.findViewById(R.id.name_val)
        val playerYear: TextView = itemView.findViewById(R.id.year_val)
        val playerRank: TextView = itemView.findViewById(R.id.rank_val)

        fun bind(item: Player) {
            playerName.text = item.playerId.toString() + item.playerName
            playerYear.text = item.playerYear.toString()
            playerRank.text = item.playerRank.toString()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater =
                    LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(
                        R.layout.recyclerview_item,
                        parent, false
                    )
                return ViewHolder(view)
            }
        }

    }

    var data =  listOf<Player>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}

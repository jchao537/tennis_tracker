package com.example.tennis_tracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tennis_tracker.database.Player

class PlayerManagerAdapter internal constructor(context: Context) : RecyclerView.Adapter<PlayerManagerAdapter.PlayerViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var players = emptyList<Player>() // Cached copy of words

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return PlayerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val current = players[position]
        holder.playerItemView.text = current.playerName
    }

    internal fun setPlayers(players: List<Player>) {
        this.players = players
        notifyDataSetChanged()
    }

    override fun getItemCount() = players.size
}

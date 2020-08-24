package com.example.tennis_tracker.database

import androidx.lifecycle.LiveData

class PlayerRepository(private val playerDao: PlayerDatabaseDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allPlayers: LiveData<List<Player>> = playerDao.getAllPlayers()

    suspend fun insert(player: Player) {
        playerDao.insert(player)
    }
}

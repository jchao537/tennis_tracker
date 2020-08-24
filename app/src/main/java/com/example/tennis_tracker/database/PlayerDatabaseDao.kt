package com.example.tennis_tracker.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlayerDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(player: Player)

    @Update
    fun update(player: Player)

    @Query("SELECT * from player_table WHERE playerId = :key")
    fun get(key: Long): Player?

    @Query("DELETE FROM player_table")
    fun clear()

    @Query("SELECT * FROM player_table ORDER BY playerId DESC LIMIT 1")
    fun getLastPlayer(): Player?

    @Query("SELECT * FROM player_table ORDER BY playerId DESC")
    fun getAllPlayers(): LiveData<List<Player>>
}
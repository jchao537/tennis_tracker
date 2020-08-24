package com.example.tennis_tracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class Player (
    @PrimaryKey(autoGenerate = true) var playerId: Long,
    @ColumnInfo var playerName: String?,
    @ColumnInfo var playerYear: Int?,
    @ColumnInfo var playerRank: Int?
)
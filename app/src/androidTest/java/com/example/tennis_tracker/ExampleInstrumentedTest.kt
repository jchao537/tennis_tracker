package com.example.tennis_tracker

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.tennis_tracker.database.PlayerDatabase
import com.example.tennis_tracker.database.PlayerDatabaseDao
import com.example.tennis_tracker.database.Player
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var playerDao: PlayerDatabaseDao
    private lateinit var db: PlayerDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, PlayerDatabase::class.java).build()
        playerDao = db.playerDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val player1 = Player(0, "Federer", 12, 1)
        playerDao.insert(player1)
        var lastPlayer = playerDao.getLastPlayer()
        assertEquals(lastPlayer?.playerName, "Federer")
        assertEquals(lastPlayer?.playerRank, 1)
        val player2 = Player(1, "Djokovic", 11, 2)
        playerDao.insert(player2)
        lastPlayer = playerDao.getLastPlayer()
        assertEquals(lastPlayer?.playerName, "Djokovic")
        playerDao.clearAllPlayers()
        lastPlayer = playerDao.getLastPlayer()
        assertEquals(lastPlayer?.playerId, null)
        assertEquals(lastPlayer?.playerName, null)
        assertEquals(lastPlayer?.playerRank, null)
        assertEquals(lastPlayer?.playerYear, null)

    }
}
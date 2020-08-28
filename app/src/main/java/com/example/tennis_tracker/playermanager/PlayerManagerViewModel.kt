package com.example.tennis_tracker.playermanager

import android.app.Application
import androidx.lifecycle.*
import com.example.tennis_tracker.database.Player
import com.example.tennis_tracker.database.PlayerDatabase
import com.example.tennis_tracker.database.PlayerDatabaseDao
import com.example.tennis_tracker.database.PlayerRepository
import com.example.tennis_tracker.formatPlayers
import kotlinx.coroutines.*
import timber.log.Timber

class PlayerManagerViewModel(val database: PlayerDatabaseDao, application: Application) : AndroidViewModel(application) {

    private val repository: PlayerRepository
    private val allPlayers: LiveData<List<Player>>
    val players = database.getAllPlayers()

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToAdd = MutableLiveData<Boolean>()
    val navigateToAdd: LiveData<Boolean>
        get() = _navigateToAdd

    fun onFabClicked() {
        _navigateToAdd.value = true
    }

    fun onNavigatedToAdd() {
        _navigateToAdd.value = false
    }

    fun onClear() {
        Timber.i("Ready to clear")
        uiScope.launch {
            // Clear the database table.
            clear()
        }
        Timber.i("Cleared")
    }

    init {
        val wordsDao = PlayerDatabase.getDatabase(application).playerDatabaseDao
        repository = PlayerRepository(wordsDao)
        allPlayers = repository.allPlayers
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(player: Player) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(player)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clearAllPlayers()
        }
    }
}
package com.example.tennis_tracker.addnewplayer

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tennis_tracker.database.Player
import com.example.tennis_tracker.database.PlayerDatabaseDao
import kotlinx.coroutines.*
import timber.log.Timber

class AddNewPlayerViewModel(val database: PlayerDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var newPlayer = MutableLiveData<Player?>()

    private val _navigateToManager = MutableLiveData<Boolean>()
    val navigateToManager: LiveData<Boolean>
        get() = _navigateToManager

    fun onSaveClicked() {
        _navigateToManager.value = true
    }

    fun onNavigatedToManager() {
        _navigateToManager.value = false
    }

    fun saveNewPlayer(player: Player) {
        viewModelScope.launch{
            insert(player)
            Timber.i("New Player Added 1")
        }
    }

    private suspend fun insert(player: Player) {
        withContext(Dispatchers.IO) {
            database.insert(player)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

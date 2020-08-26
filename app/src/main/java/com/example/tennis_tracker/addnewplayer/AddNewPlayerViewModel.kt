package com.example.tennis_tracker.addnewplayer

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tennis_tracker.database.Player
import com.example.tennis_tracker.database.PlayerDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

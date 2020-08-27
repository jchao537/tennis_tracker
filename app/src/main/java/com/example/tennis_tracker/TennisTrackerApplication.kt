package com.example.tennis_tracker

import android.app.Application
import timber.log.Timber

class TennisTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}

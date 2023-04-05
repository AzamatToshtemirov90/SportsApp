package com.azamat.sportsapp.app

import android.app.Application

class SportsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinConfig.start(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        KoinConfig.stop()
    }


}
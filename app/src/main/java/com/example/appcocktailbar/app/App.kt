package com.example.appcocktailbar.app

import android.app.Application
import androidx.room.Room
import com.example.appcocktailbar.data.room.CocktailDatabase
import com.example.appcocktailbar.di.dataModule
import com.example.appcocktailbar.di.domainModule
import com.example.appcocktailbar.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(dataModule, domainModule, uiModule))
        }
    }

}
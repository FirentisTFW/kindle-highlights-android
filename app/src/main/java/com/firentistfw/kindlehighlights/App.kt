package com.firentistfw.kindlehighlights

import android.app.Application
import com.firentistfw.kindlehighlights.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                androidModule,
                localStorageModule,
                repositoryModule,
                servicesModule,
                viewModelModule,
            )
        }
    }
}
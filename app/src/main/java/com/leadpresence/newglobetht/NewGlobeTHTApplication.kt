package com.leadpresence.newglobetht

import android.app.Application
import com.leadpresence.newglobetht.di.appModule
import com.leadpresence.newglobetht.di.mapperModule
import com.leadpresence.newglobetht.di.networkModule
import com.leadpresence.newglobetht.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PupilManagerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PupilManagerApp)
            modules(listOf(appModule, networkModule,mapperModule,repositoryModule))
        }
    }
}
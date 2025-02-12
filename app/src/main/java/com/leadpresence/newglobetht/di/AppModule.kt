package com.leadpresence.newglobetht.di

import androidx.room.Room
import com.leadpresence.newglobetht.data.local.dao.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "new_globe_db"
        ).build()
    }
}











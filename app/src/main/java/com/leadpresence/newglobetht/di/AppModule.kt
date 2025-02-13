package com.leadpresence.newglobetht.di

import androidx.room.Room
import com.leadpresence.newglobetht.data.local.dao.PupilDao
import com.leadpresence.newglobetht.data.local.dao.RemoteKeyDao
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

    // Provide the DAO
    single<PupilDao> {
        get<AppDatabase>().getImageDao()
    }
    // Provide the DAO
    single<RemoteKeyDao> {
        get<AppDatabase>().getRemoteKeyDao()
    }
}











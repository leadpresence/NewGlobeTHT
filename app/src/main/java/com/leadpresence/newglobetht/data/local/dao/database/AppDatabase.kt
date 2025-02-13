package com.leadpresence.newglobetht.data.local.dao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leadpresence.newglobetht.data.local.dao.PupilDao
import com.leadpresence.newglobetht.data.local.dao.RemoteKeyDao
import com.leadpresence.newglobetht.data.local.entity.PupilEntity
import com.leadpresence.newglobetht.data.local.entity.RemoteKey


@Database(entities = [PupilEntity::class, RemoteKey::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
//Already provided
//    companion object {
//        fun getInstance(context: Context) = Room.databaseBuilder(
//            context,
//            AppDatabase::class.java,
//            "new_globe_db"
//        )
//            .build()
//    }
    abstract fun getImageDao(): PupilDao

    abstract fun getRemoteKeyDao(): RemoteKeyDao


}
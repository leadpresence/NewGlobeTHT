package com.leadpresence.newglobetht.data.local.dao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leadpresence.newglobetht.data.local.dao.PupilDao
import com.leadpresence.newglobetht.data.local.dao.PupilEntity
//import com.leadpresence.newglobetht.data.local.dao.RemoteKeyDao
//import com.leadpresence.newglobetht.data.local.entity.PupilEntity
//import com.leadpresence.newglobetht.data.local.entity.RemoteKey


@Database(entities = [PupilEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getImageDao(): PupilDao

//    abstract fun getRemoteKeyDao(): RemoteKeyDao


}
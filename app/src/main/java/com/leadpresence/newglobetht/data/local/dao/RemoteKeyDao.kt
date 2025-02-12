package com.leadpresence.newglobetht.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leadpresence.newglobetht.data.local.entity.RemoteKey

@Dao
interface RemoteKeyDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<RemoteKey>)

    @Query("SELECT * FROM REMOTEKEY WHERE `id`=:id")
    suspend fun getRemoteKey(id: Long): RemoteKey?

    @Query("DELETE FROM REMOTEKEY WHERE `query`=:q")
    suspend fun deleteRemoteKeyQuery(q: String)

}
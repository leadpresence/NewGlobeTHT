package com.leadpresence.newglobetht.data.local.dao

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.*
import androidx.room.Insert
import androidx.room.Query
import com.leadpresence.newglobetht.data.local.entity.PupilEntity
import com.leadpresence.newglobetht.domain.model.Pupil
import kotlinx.coroutines.flow.Flow

@Dao
interface PupilDao {
    @Query("SELECT * FROM pupils")
//    fun getPupils(): Flow<PagingData<Pupil>>
    fun getPupils(): PagingSource<Int,PupilEntity>

    @Query("SELECT * FROM pupils WHERE id = :id")
    fun getPupilById(id: Long): Flow<PupilEntity>

    @Insert
    suspend fun insertPupil(pupil: PupilEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<PupilEntity>)

    @Update
    suspend fun updatePupil(pupil: PupilEntity)

    @Delete
    suspend fun deletePupil(pupil: PupilEntity)

    @Query("SELECT COUNT(*) FROM pupils WHERE `pupilId`=:id")
    suspend fun getCountCorrespondingToQuery(id: Int): Int
}
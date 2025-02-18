package com.leadpresence.newglobetht.data.local.dao

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.*
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//import com.leadpresence.newglobetht.data.local.entity.PupilEntity
//import kotlinx.coroutines.flow.Flow

//@Dao
//interface PupilDao {
//    @Query("SELECT * FROM pupils")
////    fun getPupils(): Flow<PagingData<Pupil>>
//    fun getPupils(): PagingSource<Int,PupilEntity>
//
//    @Query("SELECT * FROM pupils WHERE id = :id")
//    fun getPupilById(id: Long): Flow<PupilEntity>

//    @Insert
//    suspend fun insertPupil(pupil: PupilEntity)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(list: List<PupilEntity>)
//
//    @Update
//    suspend fun updatePupil(pupil: PupilEntity)
//
//    @Delete
//    suspend fun deletePupil(pupil: PupilEntity)
//
//    @Query("SELECT COUNT(*) FROM pupils WHERE `id`=:id")
//    suspend fun getCountCorrespondingToQuery(id: Int): Int
//}

@Dao
interface PupilDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pupils: List<PupilEntity>)

    @Query("SELECT * FROM pupils")
    fun pagingSource(): PagingSource<Int, PupilEntity>

    @Query("SELECT * FROM pupils WHERE id = :id")
    fun getPupilById(id: Int): Flow<PupilEntity>


    @Insert
    suspend fun insert(pupil: PupilEntity)

    @Update
    suspend fun update(pupil: PupilEntity)

    @Delete
    suspend fun delete(pupil: PupilEntity)

    @Query("DELETE FROM pupils WHERE pupilId = :pupilId")
    suspend fun deleteById(pupilId: Int)
    // Add clearAll and proper insertAll
    @Query("DELETE FROM pupils")
    suspend fun clearAll()
}

@Entity(tableName = "pupils")
data class PupilEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val pupilId: Long,
    val country: String,
    val name: String,
    val image: String,
    val latitude: Double,
    val longitude: Double
)
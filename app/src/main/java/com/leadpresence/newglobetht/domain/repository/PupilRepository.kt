package com.leadpresence.newglobetht.domain.repository

import com.leadpresence.newglobetht.domain.model.Pupil
import kotlinx.coroutines.flow.Flow

interface PupilRepository {
    suspend fun getPupils(page: Int, pageSize: Int): Flow<List<Pupil>>
    suspend fun getPupil(id: Long): Flow<Pupil>
    suspend fun insertPupil(pupil: Pupil)
    suspend fun updatePupil(pupil: Pupil)
    suspend fun deletePupil(pupil: Pupil)
}
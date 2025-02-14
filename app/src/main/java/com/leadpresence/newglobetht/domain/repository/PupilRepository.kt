package com.leadpresence.newglobetht.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.leadpresence.newglobetht.domain.model.Pupil
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PupilRepository {
    suspend fun getPupils(): Pager<Int,Pupil>
    suspend fun getRemoteMediatorPupils(): Flow<PagingData<Pupil>>
    suspend fun getPupilById(id: Long):Flow<Pupil>
    suspend fun createPupil(pupil: Pupil):Flow<Pupil>
    suspend fun updatePupil(pupil: Pupil): Flow<Pupil>
    suspend fun deletePupil(pupil: Pupil):Flow<Unit>
}

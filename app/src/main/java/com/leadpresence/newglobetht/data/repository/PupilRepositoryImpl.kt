package com.leadpresence.newglobetht.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.leadpresence.newglobetht.data.local.dao.PupilDao
import com.leadpresence.newglobetht.data.local.dao.RemoteKeyDao
import com.leadpresence.newglobetht.data.local.entity.toEntity
import com.leadpresence.newglobetht.data.local.mapper.PupilDTOToPupilMapper
import com.leadpresence.newglobetht.data.local.mapper.PupilEntityToPupilMapper
import com.leadpresence.newglobetht.data.local.mapper.PupilToPupilEntityMappper
import com.leadpresence.newglobetht.data.remote.network.PupilApi
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.repository.PupilRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class PupilRepositoryImpl(
      private val apiService: PupilApi,
      private val pupilDao: PupilDao,
      private val remoteKeyDao: RemoteKeyDao,
      private val pupilDTOToPupilMapperModule: PupilDTOToPupilMapper,
      private val pupilEntityToPupilMapper: PupilEntityToPupilMapper,
) : PupilRepository {
    override suspend fun getPupils(): Pager<Int, Pupil> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                PupilsPagingSource(
                    apiService = apiService,
                    pupilDTOToPupilMapperModule = pupilDTOToPupilMapperModule
                )
            }
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getRemoteMediatorPupils(): Flow<PagingData<Pupil>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = true,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                pupilDao.getPupils()
            },
            remoteMediator = PupilRemoteMediator(
                pupilDao = pupilDao,
                remoteKeyDao = remoteKeyDao,
                apiService = apiService,
                pupilId = 0
            )
        ).flow
            .map { it ->
                it.map {
                    pupilEntityToPupilMapper.map(it)
                }
            }
    }

    override suspend fun createPupil(pupil: Pupil):  Flow<Pupil> = flow {
        try {
            pupilDao.insertPupil(pupil.toEntity())
        } catch (e: Exception) {
            throw Exception("Error inserting pupil: ${e.message}")
        }
    }
    override suspend fun getPupilById(id: Long): Flow<Pupil>  = flow{
         pupilDao.getPupilById(id)
            .map { entity ->
               pupilEntityToPupilMapper.map(entity)
            }
            .catch { exception ->
                throw Exception("Error fetching pupil with id $id: ${exception.message}")
            }
    }

    override suspend fun updatePupil(pupil: Pupil):Flow<Pupil> = flow {
        try {
            val existingPupil = pupilDao.getPupilById(pupil.id)
                .first()
            if(existingPupil.pupilId==pupil.pupilId){
                pupilDao.updatePupil(pupil.toEntity())
            }// Get the first emission

        } catch (e: Exception) {
            throw Exception("Error updating pupil with id ${pupil.id}: ${e.message}")
        }
    }

    override suspend fun deletePupil(pupil: Pupil):Flow<Unit> = flow {
        try {
            val existingPupil = pupilDao.getPupilById(pupil.id).first()

            pupilDao.deletePupil(pupil.toEntity())
        } catch (e: Exception) {
            throw Exception("Error deleting pupil with id ${pupil.id}: ${e.message}")
        }
    }

}


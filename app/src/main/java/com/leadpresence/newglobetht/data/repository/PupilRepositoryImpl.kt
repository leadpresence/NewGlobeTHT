package com.leadpresence.newglobetht.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.leadpresence.newglobetht.data.local.dao.PupilDao
import com.leadpresence.newglobetht.data.local.dao.RemoteKeyDao
import com.leadpresence.newglobetht.data.local.mapper.PupilDTOToPupilMapper
import com.leadpresence.newglobetht.data.local.mapper.PupilEntityToPupilMapper
import com.leadpresence.newglobetht.data.remote.network.PupilApi
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.repository.PupilRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PupilRepositoryImpl(
      private val apiService: PupilApi,
      private val pupilDao: PupilDao,
      private val remoteKeyDao: RemoteKeyDao,
      private val mapper: PupilDTOToPupilMapper,
      private val pupilEntityToPupilMapper: PupilEntityToPupilMapper
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
            mapper = mapper
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
        .map {
          it.map {
              pupilEntityToPupilMapper.map(it)
          }
        }
    }


    override suspend fun getPupilById(id: Long): Flow<Pupil> {
      TODO("Not yet implemented")
    }

    override suspend fun insertPupil(pupil: Pupil) {
      TODO("Not yet implemented")
    }

    override suspend fun updatePupil(pupil: Pupil) {
      TODO("Not yet implemented")
    }

    override suspend fun deletePupil(pupil: Pupil) {
      TODO("Not yet implemented")
    }


  }
package com.leadpresence.newglobetht.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator

import com.leadpresence.newglobetht.data.local.dao.PupilDao
import com.leadpresence.newglobetht.data.local.dao.RemoteKeyDao
import com.leadpresence.newglobetht.data.local.entity.PupilEntity
import com.leadpresence.newglobetht.data.local.entity.RemoteKey
import com.leadpresence.newglobetht.data.local.mapper.PupilDTOToPupilEntityMapper
import com.leadpresence.newglobetht.data.local.mapper.mapAll
import com.leadpresence.newglobetht.data.remote.network.PupilApi


@OptIn(ExperimentalPagingApi::class)
class PupilRemoteMediator(
    private  val pupilId:Int,
    private val pupilDao: PupilDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val apiService: PupilApi
) : RemoteMediator<Int, PupilEntity>() {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun initialize(): InitializeAction {
        val hasLocalData = pupilDao.getCountCorrespondingToQuery(pupilId) > 0
        return if (hasLocalData) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PupilEntity>
    ): MediatorResult {
        val mapper = PupilDTOToPupilEntityMapper()
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = state.anchorPosition?.let {
                    state.closestItemToPosition(it)?.let {
                        remoteKeyDao.getRemoteKey(it.id)
                    }
                }
                remoteKey?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKey = state.pages.firstOrNull {
                    it.data.isNotEmpty()
                }?.data?.firstOrNull()?.let {
                    remoteKeyDao.getRemoteKey(it.id)
                }
                remoteKey?.prevKey ?: return MediatorResult.Success(remoteKey != null)
            }

            LoadType.APPEND -> {
                val remoteKey = state.pages.lastOrNull {
                    it.data.isNotEmpty()
                }?.data?.lastOrNull()?.let {
                    remoteKeyDao.getRemoteKey(it.id)
                }
                remoteKey?.nextKey ?: return MediatorResult.Success(remoteKey != null)
            }
        }

        if (pupilDao.getCountCorrespondingToQuery(pupilId) > page.times(state.config.pageSize)) {
            return MediatorResult.Success(false)
        }

        return try {
            val response = apiService.getPupils()
            val remoteImages = response.items.distinctBy { it.pupilId }
            val endOfPaginationReached = remoteImages.size < state.config.pageSize
            val prevPage = if (page > 1) page - 1 else null
            val nextPage = if (endOfPaginationReached) null else page + 1

            val remoteKeys = remoteImages.map {
                RemoteKey(
                    id = it.pupilId,
                    prevKey = prevPage,
                    nextKey = nextPage,

                )
            }
            remoteKeyDao.insertAll(remoteKeys)
            pupilDao.insertAll(mapper.mapAll(remoteImages))
            MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
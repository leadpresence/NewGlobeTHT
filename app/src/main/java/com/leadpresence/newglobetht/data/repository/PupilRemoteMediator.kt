package com.leadpresence.newglobetht.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator

import com.leadpresence.newglobetht.data.local.dao.PupilDao
import com.leadpresence.newglobetht.data.local.dao.PupilEntity
//import com.leadpresence.newglobetht.data.local.dao.RemoteKeyDao
//import com.leadpresence.newglobetht.data.local.entity.PupilEntity
//import com.leadpresence.newglobetht.data.local.entity.RemoteKey
//import com.leadpresence.newglobetht.data.local.mapper.PupilDTOToPupilEntityMapper
import com.leadpresence.newglobetht.data.local.mapper.mapAll
import com.leadpresence.newglobetht.data.model.PupilDto
import com.leadpresence.newglobetht.data.remote.network.PupilApi
import okhttp3.internal.concurrent.TaskRunner.Companion.logger

@OptIn(ExperimentalPagingApi::class)
class PupilRemoteMediator(
    private val api: PupilApi,
    private val dao: PupilDao
) : RemoteMediator<Int, PupilEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PupilEntity>
    ): MediatorResult {
        return try {
            // Log the load type
            logger.info("Loading pupils with loadType: $loadType")
            // Calculate page number
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    logger.info("Refreshing pupils data - clearing database")
                    dao.clearAll()
                    1
                }
                LoadType.PREPEND -> {
                    logger.info("Prepend requested - skipping as we only append")
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
                LoadType.APPEND -> {
                    logger.info("Appending pupils data - loading page ${  state.config.pageSize + 1}")
                    val lastItem = state.lastItemOrNull()
                    lastItem?.pupilId?.let { id ->
                        (id / state.config.pageSize) + 1
                    } ?: 1
                }
            }
            logger.info("Making API call to fetch pupils for page: $page")
            // Fetch from API
            val response = api.getPupils(page)
            logger.info("Received ${response.items.size} pupils from API")

            // Convert to entities and insert
            val entities = response.items.map { dto ->
                PupilEntity(
                    id=-1,
                    pupilId = dto.pupilId,
                    name = dto.name,
                    country = dto.country,
                    image = dto.image,
                    latitude = dto.latitude,
                    longitude = dto.longitude
                )
            }
            logger.info("Inserting ${entities.size} pupils into database")
            dao.insertAll(entities)

            // Check end of pagination
            val endOfPaginationReached = page >= response.totalPages
            logger.info("End of pagination reached: $endOfPaginationReached (page $page of ${response.totalPages})")

            MediatorResult.Success(
                endOfPaginationReached = endOfPaginationReached
            )
        } catch (e: Exception) {
            logger.info("Error loading pupils $e",)
            MediatorResult.Error(e)
        }
    }
}
package com.leadpresence.newglobetht.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.leadpresence.newglobetht.data.local.mapper.PupilDTOToPupilMapper
import com.leadpresence.newglobetht.data.local.mapper.mapAll
import com.leadpresence.newglobetht.data.remote.network.PupilApi
import com.leadpresence.newglobetht.domain.model.Pupil

class PupilsPagingSource(
    private val apiService: PupilApi,
    private val mapper: PupilDTOToPupilMapper
) : PagingSource<Int, Pupil>() {
    override fun getRefreshKey(state: PagingState<Int, Pupil>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pupil> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        return try {

            val pupils = apiService.getPupils(page = page)
            LoadResult.Page(
                data = mapper.mapAll(pupils.items),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (pupils.items.size < pageSize) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}
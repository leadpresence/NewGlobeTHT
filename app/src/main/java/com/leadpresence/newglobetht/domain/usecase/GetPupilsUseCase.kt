package com.leadpresence.newglobetht.domain.usecase

import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.repository.PupilRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetPupilsUseCase(
    private val repository: PupilRepository
) {
//    suspend operator fun invoke(page: Int, pageSize: Int): Flow<Result<List<Pupil>>> {
//        return repository.getPupils(page, pageSize)
//            .map { Result.success(it) }
//            .catch { emit(Result.failure(it)) }
//    }
}
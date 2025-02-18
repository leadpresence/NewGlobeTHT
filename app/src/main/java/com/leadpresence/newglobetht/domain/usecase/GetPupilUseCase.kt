package com.leadpresence.newglobetht.domain.usecase

import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.repository.PupilRepository
import kotlinx.coroutines.flow.Flow
//
//class GetPupilUseCase(
//    private val repository: PupilRepository
//) {
//
//    suspend operator fun invoke(id: Long): Result<Flow<Pupil>> {
//        return try {
//            Result.success(repository.getPupilById(id))
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//}
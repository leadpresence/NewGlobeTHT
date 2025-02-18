package com.leadpresence.newglobetht.domain.usecase
//
//import com.leadpresence.newglobetht.domain.model.Pupil
//import com.leadpresence.newglobetht.domain.repository.PupilRepository
//
//
//class DeletePupilUseCase(
//    private val repository: PupilRepository
//) {
//    suspend operator fun invoke(pupil: Pupil): Result<Unit> {
//        return try {
//            repository.deletePupil(pupil)
//            Result.success(Unit)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//}
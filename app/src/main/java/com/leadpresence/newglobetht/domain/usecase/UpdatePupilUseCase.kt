//package com.leadpresence.newglobetht.domain.usecase
//
//import com.leadpresence.newglobetht.domain.model.Pupil
//import com.leadpresence.newglobetht.domain.repository.PupilRepository
//
//class UpdatePupilUseCase(
//    private val repository: PupilRepository,
//    private val createPupilUseCase: CreatePupilUseCase // Reuse validation logic
//) {
//    suspend operator fun invoke(pupil: Pupil): Result<Unit> {
//        return try {
//            // Validate before updating
//            when (val validationResult = createPupilUseCase.validatePupil(pupil)) {
//                is ValidationResult.Success -> {
//                    repository.updatePupil(pupil)
//                    Result.success(Unit)
//                }
//                is ValidationResult.Error -> {
//                    Result.failure(IllegalArgumentException(validationResult.message))
//                }
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//}
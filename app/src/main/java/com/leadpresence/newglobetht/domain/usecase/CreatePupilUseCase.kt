package com.leadpresence.newglobetht.domain.usecase
//
//import com.leadpresence.newglobetht.domain.model.Pupil
//import com.leadpresence.newglobetht.domain.repository.PupilRepository
//
//class CreatePupilUseCase(
//    private val repository: PupilRepository
//) {
//    suspend operator fun invoke(pupil: Pupil): Result<Unit> {
//        return try {
//            repository.createPupil(pupil)
//            Result.success(Unit)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//
//
//    // Validation logic
//    fun validatePupil(pupil: Pupil): ValidationResult {
//        return when {
//            pupil.name.isBlank() -> ValidationResult.Error("Name cannot be empty")
//            pupil.image.isBlank() -> ValidationResult.Error("Image URL cannot be empty")
//            !isValidLatitude(pupil.latitude) -> ValidationResult.Error("Invalid latitude")
//            !isValidLongitude(pupil.longitude) -> ValidationResult.Error("Invalid longitude")
//            else -> ValidationResult.Success
//        }
//    }
//
//    private fun isValidLatitude(latitude: Double): Boolean {
//        return latitude in -90.0..90.0
//    }
//
//    private fun isValidLongitude(longitude: Double): Boolean {
//        return longitude in -180.0..180.0
//    }
//}
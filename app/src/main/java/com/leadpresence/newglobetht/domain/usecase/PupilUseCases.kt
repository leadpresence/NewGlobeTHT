package com.leadpresence.newglobetht.domain.usecase

import androidx.paging.PagingData
import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.repository.PupilRepository
import kotlinx.coroutines.flow.Flow

//
//data class PupilUseCases(
//    val getPupils: GetPupilsUseCase,
//    val getPupil: GetPupilUseCase,
//    val createPupil: CreatePupilUseCase,
//    val updatePupil: UpdatePupilUseCase,
//    val deletePupil: DeletePupilUseCase
//)


class PupilUseCases(
    private val repository: PupilRepository
) {
    // Get paginated pupils
    val getPupils = GetPupilsUseCase(repository)

    // Get single pupil
    class GetPupilUseCase(private val repository: PupilRepository) {
        suspend operator fun invoke(pupilId: Int): Pupil {
            return repository.getPupil(pupilId)
        }
    }

    // Create pupil
    class CreatePupilUseCase(private val repository: PupilRepository) {
        suspend operator fun invoke(pupil: Pupil) {
            repository.createPupil(pupil)
        }
    }

    // Update pupil
    class UpdatePupilUseCase(private val repository: PupilRepository) {
        suspend operator fun invoke(pupil: Pupil) {
            repository.updatePupil(pupil)
        }
    }

    // Delete pupil
    class DeletePupilUseCase(private val repository: PupilRepository) {
        suspend operator fun invoke(pupilId: Int) {
            repository.deletePupil(pupilId)
        }
    }
}

// Separate class for paginated flow
class GetPupilsUseCase(
    private val repository: PupilRepository
) {
    operator fun invoke(): Flow<PagingData<Pupil>> {
        return repository.getPupils()
    }
}
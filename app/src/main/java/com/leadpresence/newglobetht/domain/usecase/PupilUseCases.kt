package com.leadpresence.newglobetht.domain.usecase

data class PupilUseCases(
    val getPupils: GetPupilsUseCase,
    val getPupil: GetPupilUseCase,
    val createPupil: CreatePupilUseCase,
    val updatePupil: UpdatePupilUseCase,
    val deletePupil: DeletePupilUseCase
)
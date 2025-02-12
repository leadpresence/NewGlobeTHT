package com.leadpresence.newglobetht.data.local.mapper

import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.model.PupilDTO

class PupilDTOToPupilMapper(
    private val query: String
) : MapperPaging<PupilDTO, Pupil> {
    override fun map(from: PupilDTO): Pupil {
        return Pupil(
            id = from.pupilId,
            name=from.name,
            imageUrl = from.image,
            latitude = from.latitude,
            longitude = from.longitude,
        )
    }
}
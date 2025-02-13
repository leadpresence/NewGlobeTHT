package com.leadpresence.newglobetht.data.local.mapper

import com.leadpresence.newglobetht.domain.model.Pupil
import com.leadpresence.newglobetht.domain.model.PupilDTO

class PupilDTOToPupilMapper(
) : MapperPaging<PupilDTO, Pupil> {
    override fun map(from: PupilDTO): Pupil {
        return Pupil(
            id = from.pupilId,
            pupilId = from.pupilId,
            name=from.name,
            image = from.image,
            latitude = from.latitude,
            longitude = from.longitude,
            country = from.country,
        )
    }
}
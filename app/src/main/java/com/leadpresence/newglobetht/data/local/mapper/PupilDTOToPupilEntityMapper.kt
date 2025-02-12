package com.leadpresence.newglobetht.data.local.mapper

import com.leadpresence.newglobetht.data.local.entity.PupilEntity
import com.leadpresence.newglobetht.domain.model.PupilDTO

class PupilDTOToPupilEntityMapper(
) : MapperPaging<PupilDTO, PupilEntity> {
    override fun map(from: PupilDTO): PupilEntity {
        return PupilEntity(
            id = from.pupilId,
            name=from.name,
            imageUrl = from.image,
            latitude = from.latitude,
            longitude = from.longitude,
        )
    }
}
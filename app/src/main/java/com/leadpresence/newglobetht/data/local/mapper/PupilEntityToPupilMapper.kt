package com.leadpresence.newglobetht.data.local.mapper

import com.leadpresence.newglobetht.data.local.entity.PupilEntity
import com.leadpresence.newglobetht.domain.model.Pupil

class PupilEntityToPupilMapper(
    private val query: String
) : MapperPaging<PupilEntity, Pupil> {
    override fun map(from: PupilEntity): Pupil {
        return Pupil(
            id = from.id,
            name=from.name,
            imageUrl = from.imageUrl,
            latitude = from.latitude,
            longitude = from.longitude,
        )
    }
}
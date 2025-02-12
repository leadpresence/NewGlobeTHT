package com.leadpresence.newglobetht.data.local.mapper

import com.leadpresence.newglobetht.data.local.entity.PupilEntity
import com.leadpresence.newglobetht.domain.model.Pupil

class PupilToPupilEntityMappper:MapperPaging<Pupil,PupilEntity>  {
    override fun map(from: Pupil): PupilEntity {
        return PupilEntity(
            id= from.id,
            name = from.name,
            imageUrl = from.imageUrl,
            latitude = from.latitude,
            longitude = from.longitude
        )
    }


}
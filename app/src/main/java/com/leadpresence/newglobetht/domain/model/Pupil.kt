package com.leadpresence.newglobetht.domain.model

import com.leadpresence.newglobetht.data.local.entity.PupilEntity

data class Pupil(
    val id: Long = 0,
    val name: String,
    val pupilId: Long,
    val country: String,
    val image: String,
    val latitude: Double,
    val longitude: Double
)

    class PupilList(
        val items: MutableList<Pupil>
    )

//fun PupilEntity.toDomain(): Pupil {
//    return Pupil(
//        id = id,
//        pupilId = pupilId,
//        name=name,
//        image = image,
//        latitude = latitude,
//        longitude = longitude,
//        country = country,
//}
package com.leadpresence.newglobetht.domain.model

//import com.leadpresence.newglobetht.data.local.entity.PupilEntity

data class Pupil(
    val pupilId: Long,
    val name: String,
    val country: String,
    val image: String,
    val latitude: Double,
    val longitude: Double
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
package com.leadpresence.newglobetht.data.local.mapper

import com.leadpresence.newglobetht.data.local.dao.PupilEntity
import com.leadpresence.newglobetht.data.model.PupilDto
import com.leadpresence.newglobetht.domain.model.Pupil

// Data layer converters
fun PupilDto.toEntity() = PupilEntity(
    id=0,
    pupilId = pupilId,
    name = name,
    country = country,
    image = image,
    latitude = latitude,
    longitude = longitude
)

fun PupilEntity.toDto() = PupilDto(
    pupilId = pupilId,
    name = name,
    country = country,
    image = image,
    latitude = latitude,
    longitude = longitude
)

// Domain layer converters
fun PupilDto.toDomain() = Pupil(
    pupilId = pupilId,
    name = name,
    country = country,
    image = image,
    latitude = latitude,
    longitude = longitude
)

fun Pupil.toDto() = PupilDto(
    pupilId = pupilId,
    name = name,
    country = country,
    image = image,
    latitude = latitude,
    longitude = longitude
)

fun PupilEntity.toDomain() = Pupil(
    pupilId = pupilId,
    name = name,
    country = country,
    image = image,
    latitude = latitude,
    longitude = longitude
)

fun Pupil.toEntity() = PupilEntity(
    id=0,
    pupilId = pupilId,
    name = name,
    country = country,
    image = image,
    latitude = latitude,
    longitude = longitude
)
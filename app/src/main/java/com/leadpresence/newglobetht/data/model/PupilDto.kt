package com.leadpresence.newglobetht.data.model

import com.leadpresence.newglobetht.data.local.dao.PupilEntity


data class PupilDto(
    val pupilId: Long,
    val country: String,
    val name: String,
    val image: String,
    val latitude: Double,
    val longitude: Double
)


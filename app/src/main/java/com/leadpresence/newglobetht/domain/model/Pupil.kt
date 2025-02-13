package com.leadpresence.newglobetht.domain.model

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
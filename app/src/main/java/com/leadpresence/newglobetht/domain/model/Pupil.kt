package com.leadpresence.newglobetht.domain.model

data class Pupil(
    val id: Long = 0,
    val name: String,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double
)
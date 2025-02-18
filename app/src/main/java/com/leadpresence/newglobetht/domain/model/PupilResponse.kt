package com.leadpresence.newglobetht.domain.model

import com.leadpresence.newglobetht.data.local.dao.PupilEntity
import kotlinx.serialization.Serializable

@Serializable
data class PupilResponse (
    val items: List<PupilDTO>,
    val pageNumber: Int,
    val itemCount: Int,
    val totalPages: Int
)

@Serializable
data class PupilDTO(
    val pupilId: Long,
    val country: String,
    val name: String,
    val image: String,
    val latitude: Double,
    val longitude: Double
) {
    fun toEntity() {
        PupilEntity(
            id = 0,
            pupilId = pupilId,
            name = name,
            country = country,
            image = image,
            latitude = latitude,
            longitude = longitude
        )
    }
}

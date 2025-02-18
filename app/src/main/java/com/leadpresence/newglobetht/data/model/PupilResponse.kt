package com.leadpresence.newglobetht.data.model



data class PupilResponse(
    val items: List<PupilDto>,
    val pageNumber: Int,
    val totalPages: Int
)
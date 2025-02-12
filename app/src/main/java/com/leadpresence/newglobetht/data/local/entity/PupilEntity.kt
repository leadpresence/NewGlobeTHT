package com.leadpresence.newglobetht.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pupils")
data class PupilEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double
)
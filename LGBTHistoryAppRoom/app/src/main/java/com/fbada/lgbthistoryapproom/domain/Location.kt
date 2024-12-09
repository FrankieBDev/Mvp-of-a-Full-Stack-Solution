package com.fbada.lgbthistoryapproom.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(
    val name: String,
    val address: String,
    val about: String,
    val latitude: Double,
    val longitude: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

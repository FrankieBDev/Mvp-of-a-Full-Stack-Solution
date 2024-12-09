package com.fbada.lgbthistoryapproom.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.fbada.lgbthistoryapproom.domain.Location

data class LocationState(

    val locations: List<Location> = emptyList(),
    val name: MutableState<String> = mutableStateOf(""),
    val about: MutableState<String> = mutableStateOf(""),
    val address: MutableState<String> = mutableStateOf(""),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
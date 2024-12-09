package com.fbada.lgbthistoryapproom.presentation

import com.fbada.lgbthistoryapproom.domain.Location

sealed interface LocationsEvent {
    object SortLocations : LocationsEvent

    data class DeleteLocation(val location: Location) : LocationsEvent

    data class SaveLocations(
        val name: String,
        val about: String,
        val address: String,
        val latitude: Double,
        val longitude: Double
    ) : LocationsEvent
}

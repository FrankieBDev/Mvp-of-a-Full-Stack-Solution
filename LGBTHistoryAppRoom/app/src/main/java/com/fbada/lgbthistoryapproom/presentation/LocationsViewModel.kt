package com.fbada.lgbthistoryapproom.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fbada.lgbthistoryapproom.data.LocationDao
import com.fbada.lgbthistoryapproom.domain.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val dao: LocationDao
) : ViewModel() {

    private val isSortedByName = MutableStateFlow(true)

    var locations =
        isSortedByName.flatMapLatest { sort ->
            if (sort) {
                dao.getLocationsOrderedByName()
            } else {
                dao.getLocationsOrderedByAddress()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(LocationState())
    val state =
        combine(_state, isSortedByName, locations) { state, isSortedByName, locations ->
            state.copy(
                locations = locations
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LocationState())

    fun onEvent(event: LocationsEvent) {
        when (event) {
            is LocationsEvent.DeleteLocation -> {
                viewModelScope.launch {
                    dao.deleteLocation(event.location)
                }
            }

            is LocationsEvent.SaveLocations -> {
                val location = Location(
                    name = state.value.name.value,
                    address = state.value.address.value,
                    about = state.value.about.value,
                    latitude = state.value.latitude,
                    longitude = state.value.longitude
                )

                viewModelScope.launch {
                    dao.upsertLocation(location)
                }

                _state.update {
                    it.copy(
                        name = mutableStateOf(""),
                        address = mutableStateOf("")
                    )
                }
            }

            LocationsEvent.SortLocations -> {
                isSortedByName.value = !isSortedByName.value
            }

            else -> {}
        }
    }

}
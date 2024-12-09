package com.fbada.lgbthistoryapproom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.fbada.lgbthistoryapproom.domain.Location
import kotlinx.coroutines.flow.Flow


@Dao
interface LocationDao {
    @Upsert
    suspend fun upsertLocation(location: Location)

    @Delete
    suspend fun deleteLocation(location: Location)

    @Query("SELECT * FROM location ORDER By name ASC")
    fun getLocationsOrderedByName(): Flow<List<Location>>

    @Query("SELECT * FROM location ORDER By address ASC")
    fun getLocationsOrderedByAddress(): Flow<List<Location>>


}












package com.fbada.lgbthistoryapproom.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fbada.lgbthistoryapproom.domain.Location


@Database(
    entities = [Location::class],
    version = 1,
    exportSchema = true,
)
abstract class LocationsDatabase : RoomDatabase() {
    abstract val dao: LocationDao
}




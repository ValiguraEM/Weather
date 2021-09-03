package com.vali.weather.model.database.dao

import androidx.room.*
import com.vali.weather.model.database.entity.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("SELECT * FROM Location")
    fun getLocations(): Flow<List<Location>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg locations: Location)

    @Delete
    fun delete(location: Location)
}
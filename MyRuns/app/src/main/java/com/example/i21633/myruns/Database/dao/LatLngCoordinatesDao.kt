package com.example.i21633.myruns.Database.dao

import android.arch.persistence.room.*
import com.example.i21633.myruns.Database.entity.ExerciseEntry
import com.example.i21633.myruns.Database.entity.LatLngCoordinate

/**
 * Created by i21633 on 12/4/17.
 */

@Dao
interface LatLngCoordinatesDao {

    @Query("select * from latLngCoordinates where id = :id")
    fun getAllLatLngPoints(id : Int): List<LatLngCoordinate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLatLng(latLngCoordinate: LatLngCoordinate)

    @Query("delete from latLngCoordinates where id = :id")
    fun removeAllLatLngWithId(id : Int)

}
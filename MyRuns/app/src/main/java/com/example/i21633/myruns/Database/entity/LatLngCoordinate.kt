package com.example.i21633.myruns.Database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by i21633 on 12/4/17.
 */

@Entity(tableName = "latLngCoordinates")
data class LatLngCoordinate (
        @PrimaryKey(autoGenerate = true) var id : Int? = null,
        var exerciseEntryId : Int = 0,
        var lat : Double = 0.toDouble(),
        var lng : Double = 0.toDouble()
)
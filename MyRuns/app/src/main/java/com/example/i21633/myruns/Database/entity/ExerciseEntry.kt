package com.example.i21633.myruns.Database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.sql.Date
import java.sql.Time
import java.time.LocalDateTime

import java.util.Calendar

/**
 * Created by i21633 on 10/18/17.
 */

@Entity(tableName = "exerciseEntries")
data class ExerciseEntry(
        @ColumnInfo(name = "id")
                @PrimaryKey(autoGenerate = true) var id: Int? = null,
        @ColumnInfo(name = "mInput_type") var mInputType: Int,
        @ColumnInfo(name = "mActivity_type") var mActivityType: Int,
        @ColumnInfo(name = "mDateTime") var mDateTime: String,
        @ColumnInfo(name="mTime") var mTimeStamp: String,
        @ColumnInfo(name = "mDuration") var mDuration: Double,
        @ColumnInfo(name = "mDistance") var mDistance: Double,
        @ColumnInfo(name = "mAvg_pace") var mAvgPace: Double,
        @ColumnInfo(name = "mAvg_speed") var mAvgSpeed: Double,
        @ColumnInfo(name = "mCalorie") var mCalorie: Int,
        @ColumnInfo(name = "mClimb") var mClimb: Double,
        @ColumnInfo(name = "mComment") var mComment: String
)

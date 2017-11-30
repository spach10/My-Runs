package com.example.i21633.myruns.Database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by i21633 on 11/21/17.
 */

@Entity(tableName = "activityTypes")
data class ActivityType (
        @PrimaryKey(autoGenerate = true) var id : Int? = null,
        var name : String = ""
)
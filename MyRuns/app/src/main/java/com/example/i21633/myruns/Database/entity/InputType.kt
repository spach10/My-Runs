package com.example.i21633.myruns.Database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by i21633 on 11/14/17.
 */

@Entity(tableName = "inputTypes")
data class InputType (
        @ColumnInfo(name = "id")
            @PrimaryKey(autoGenerate = true) var id : Long = 0,
        @ColumnInfo(name = "inputType") var inputType : String = ""
)
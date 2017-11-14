package com.example.i21633.myruns.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.i21633.myruns.Database.Converters.DateConverter
import com.example.i21633.myruns.Database.dao.ExerciseEntryDao
import com.example.i21633.myruns.Database.entity.ExerciseEntry

/**
 * Created by i21633 on 10/18/17.
 */

@Database(entities = arrayOf(ExerciseEntry::class), version = 16, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseEntryDao(): ExerciseEntryDao

}

package com.example.i21633.myruns.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.i21633.myruns.Database.dao.ActivityTypeDao
import com.example.i21633.myruns.Database.dao.ExerciseEntryDao
import com.example.i21633.myruns.Database.dao.InputTypeDao
import com.example.i21633.myruns.Database.entity.ActivityType
import com.example.i21633.myruns.Database.entity.ExerciseEntry
import com.example.i21633.myruns.Database.entity.InputType

/**
 * Created by i21633 on 10/18/17.
 */

@Database(entities = arrayOf(ExerciseEntry::class, InputType::class, ActivityType::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseEntryDao() : ExerciseEntryDao
    abstract fun activityTypeDao() : ActivityTypeDao
    abstract fun inputTypeDao() : InputTypeDao

}

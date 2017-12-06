package com.example.i21633.myruns.Database.dao

import android.arch.persistence.room.*

import com.example.i21633.myruns.Database.entity.ExerciseEntry

/**
 * Created by i21633 on 10/18/17.
 */

@Dao
@TypeConverters
interface ExerciseEntryDao {

    @Query("select * from exerciseEntries")
    fun getAllExerciseEntries(): List<ExerciseEntry>

    @Query("select * from exerciseEntries where id = :exerciseEntryId")
    fun getExerciseEntryById(exerciseEntryId: Int) : ExerciseEntry

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addExercise(exerciseEntry: ExerciseEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateExerciseEntry(exerciseEntry: ExerciseEntry)

    @Delete
    fun removeExerciseEntry(exerciseEntry: ExerciseEntry)

}

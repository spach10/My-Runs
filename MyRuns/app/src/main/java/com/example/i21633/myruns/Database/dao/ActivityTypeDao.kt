package com.example.i21633.myruns.Database.dao

import android.arch.persistence.room.*
import com.example.i21633.myruns.Database.entity.ActivityType
import com.example.i21633.myruns.Database.entity.InputType

/**
 * Created by i21633 on 11/21/17.
 */

@Dao
interface ActivityTypeDao {

    @Query("select * from activityTypes")
    fun getAllActivityTypes(): List<ActivityType>

    @Query("select name from activityTypes where id = :id")
    fun getActivityType(id : Int) : String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addActivityType(activityType: ActivityType)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateActivityType(activityType: ActivityType)

    @Delete
    fun removeActivityType(activityType: ActivityType)

}
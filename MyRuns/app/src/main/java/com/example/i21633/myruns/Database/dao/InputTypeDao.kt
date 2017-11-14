package com.example.i21633.myruns.Database.dao

import android.arch.persistence.room.*
import com.example.i21633.myruns.Database.entity.InputType

/**
 * Created by i21633 on 11/14/17.
 */

@Dao
interface InputTypeDao {

    @Query("select * from inputTypes")
    fun getAllInputTypes(): List<InputType>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addInputType(inputType: InputType)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateInputType(inputType: InputType)

    @Delete
    fun removeInputType(inputType: InputType)

}
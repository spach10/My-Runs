package com.example.i21633.myruns

import android.app.Application
import android.arch.persistence.room.Room
import com.example.i21633.myruns.Database.AppDatabase
import com.example.i21633.myruns.Database.entity.ExerciseEntry
import java.util.prefs.Preferences

/**
 * Created by i21633 on 10/19/17.
 */

class App : Application() {

    companion object {
        var db: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        App.db =  Room.databaseBuilder(this, AppDatabase::class.java, "exerciseEntryDB").allowMainThreadQueries().build()
    }
}
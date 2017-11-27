package com.example.i21633.myruns.Start

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_manual_entry.*
import android.widget.AdapterView
import com.example.i21633.myruns.App
import com.example.i21633.myruns.App.Companion.db
import com.example.i21633.myruns.Database.entity.ExerciseEntry
import com.example.i21633.myruns.History.Tab2HistoryFragment
import com.example.i21633.myruns.R


class ManualEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual_entry)

        createListView()
        setClickListeners()
    }

    private fun setClickListeners() {
        mEntrySave.setOnClickListener({ v -> insertExerciseEntry(v) })
        mEntryCancel.setOnClickListener({ v -> cancelExerciseEntry(v) })
    }

    private fun insertExerciseEntry(v: View?) {
        //val application = application as? App
        try {
            App.db?.exerciseEntryDao()?.addExercise(
                    ExerciseEntry(
                            0,
                            1,
                            "Running",
                            30,
                            1.5,
                            8.0,
                            8.0,
                            315,
                            20.5,
                            171,
                            ""
                    )
            )
            //Tab2HistoryFragment
        } catch (e: Exception) {
            val message = e.message
        }
    }

    private fun cancelExerciseEntry(v: View?) {}

    private fun createListView() {

        val listItems = arrayOf("Date", "Time", "Duration", "Distance", "Calories", "Heart Rate", "Comments")
        val itemsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems)
        manual_entry_list.adapter = itemsAdapter

        manual_entry_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            when(position) {
                0 -> {
                    val dateFrag = DatePickerFragment()
                    dateFrag.show(supportFragmentManager, "datePicker")
                }
                1 -> {
                    val timeFrag = TimePickerFragment()
                    timeFrag.show(supportFragmentManager, "timePicker")
                }
                2 -> {
                    val textEditFrag = TextEditFragment()
                    textEditFrag.show(supportFragmentManager, "editText")
                }
            }

        }

    }

}



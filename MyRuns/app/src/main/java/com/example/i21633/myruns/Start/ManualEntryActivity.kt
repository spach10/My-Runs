package com.example.i21633.myruns.Start

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_manual_entry.*
import android.widget.AdapterView
import com.example.i21633.myruns.R


class ManualEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual_entry)

        createListView()
    }

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
            }

        }

    }

}



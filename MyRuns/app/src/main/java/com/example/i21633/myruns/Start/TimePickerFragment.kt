package com.example.i21633.myruns.Start

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import android.util.Log
import android.widget.TextView
import android.widget.TimePicker

import com.example.i21633.myruns.R

import java.util.Calendar

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //Use the current time as the default values for the time picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        //Create and return a new instance of TimePickerDialog
        return TimePickerDialog(activity, this, hour, minute,
                DateFormat.is24HourFormat(activity))
    }

    override fun onCancel(dialog: DialogInterface?) {
        Log.i("cancel", "time cancel")
    }

    //onTimeSet() callback method
    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {

    }
}

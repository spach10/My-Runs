package com.example.i21633.myruns.Start

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.i21633.myruns.R
import kotlinx.android.synthetic.main.fragment_tab1start.*

/**
 * Created by i21633 on 9/26/17.
 */

class Tab1StartFragment : Fragment() {

    private lateinit var _inputType : String
    private var _activityType : Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_tab1start, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }


    private fun setClickListeners() {
        startActivityButton.setOnClickListener({ openNextScreen() })
        inputTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                _inputType = parent!!.getItemAtPosition(pos).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        activityTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                _activityType = pos
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }

    private fun openNextScreen() {
        when (_inputType) {
            "Manual" -> return openManualEntryActivity()
            "GPS" -> return openMapDisplayActivity()
        }
    }

    private fun openManualEntryActivity() {
        val intent = Intent(activity, ManualEntryActivity::class.java)
        startActivity(intent)
    }

    private fun openMapDisplayActivity() {
        val intent = Intent(activity, MapDisplayActivity::class.java)
        var bundle = Bundle()
        bundle.putInt("ActivityType", _activityType)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}


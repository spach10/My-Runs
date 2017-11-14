package com.example.i21633.myruns.Start

import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
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

    lateinit var _inputType : String

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
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                _inputType = parent!!.getItemAtPosition(pos).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                //_inputType = parent!!.getItemAtPosition()
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
        startActivity(intent)
    }

}


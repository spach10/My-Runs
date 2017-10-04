package com.example.i21633.myruns

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tab1start.*

/**
 * Created by i21633 on 9/26/17.
 */

class Tab1StartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_tab1start, container, false)
        setClickListeners()
        return rootView

    }

    private fun setClickListeners() {
        startButton.setOnClickListener({ openManualEntryActivity() })
    }

    private fun openManualEntryActivity() {

    }

}


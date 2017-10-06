package com.example.i21633.myruns.Start

import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.i21633.myruns.R
import kotlinx.android.synthetic.main.fragment_tab1start.*

/**
 * Created by i21633 on 9/26/17.
 */

class Tab1StartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_tab1start, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        startActivityButton.setOnClickListener({ openManualEntryActivity() })
    }

    private fun openManualEntryActivity() {
        val intent = Intent(activity, ManualEntryActivity::class.java)
        startActivity(intent)
    }

}


package com.example.i21633.myruns.Start

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.support.v4.app.DialogFragment

import com.example.i21633.myruns.R
import kotlinx.android.synthetic.main.fragment_text_edit.*

class TextEditFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_text_edit, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        // Show soft keyboard automatically and request focus to field
        editTextForManualEntry.requestFocus()
        dialog.window.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

    }

//    companion object {
//
//        //    public TextEditFragment() {
//        //        // Empty constructor is required for DialogFragment
//        //        // Make sure not to add arguments to the constructor
//        //        // Use `newInstance` instead as shown below
//        //    }
//
//        fun newInstance(title: String): TextEditFragment {
//            val frag = TextEditFragment()
//            val args = Bundle()
//            args.putString("title", title)
//            frag.arguments = args
//            return frag
//        }
//    }
}

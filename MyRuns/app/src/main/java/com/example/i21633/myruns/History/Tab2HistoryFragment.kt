package com.example.i21633.myruns.History

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.i21633.myruns.App
import com.example.i21633.myruns.R
import kotlinx.android.synthetic.main.fragment_tab2history.*

/**
 * Created by i21633 on 9/26/17.
 */

class Tab2HistoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        createListView()
        return inflater!!.inflate(R.layout.fragment_tab2history, container, false)
    }

    private fun createListView() {
        val exerciseEntries = App.db?.exerciseEntryDao()?.getAllExerciseEntries()
        if (exerciseEntries!!.count() > 0) {
//            val listItems = arrayOf()
//            val itemsAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, listItems)
//            history_list_view.adapter = itemsAdapter
        }
    }


}

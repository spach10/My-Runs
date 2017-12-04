package com.example.i21633.myruns.History

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.i21633.myruns.App
import com.example.i21633.myruns.Database.entity.ExerciseEntry
import com.example.i21633.myruns.R
import com.example.i21633.myruns.Start.MapDisplayActivity
import kotlinx.android.synthetic.main.fragment_tab2history.*

/**
 * Created by i21633 on 9/26/17.
 */

class Tab2HistoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_tab2history, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createListView()
        setClickListener()
    }

    private fun createListView() {
        val exerciseEntries = App.db?.exerciseEntryDao()?.getAllExerciseEntries()
        if (exerciseEntries!!.count() > 0) {
            section_label.text = ""

            val listItems = ArrayList<String>()
            exerciseEntries.forEach {
                entry -> listItems.add(entry.mDateTime)
            }
            val itemsAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, listItems)
            history_list_view.adapter = itemsAdapter

        }
    }

    private fun setClickListener() {
        history_list_view.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val intent = Intent(activity, HistoryMapDisplayActivity::class.java)
                var bundle = Bundle()
                bundle.putInt("exerciseEntryId", pos + 1)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

}

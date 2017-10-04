package com.example.i21633.myruns

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat

/**
 * Created by i21633 on 9/26/17.
 */

class Tab3SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        addPreferencesFromResource(R.xml.preferences)
    }

//    private fun setPreferenceClickListeners() {
//        val profilePreference = findPreference("profile")
//
//    }

}
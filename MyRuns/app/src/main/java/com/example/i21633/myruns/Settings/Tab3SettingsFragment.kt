package com.example.i21633.myruns.Settings

import android.content.Intent
import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import com.example.i21633.myruns.R

/**
 * Created by i21633 on 9/26/17.
 */

class Tab3SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        addPreferencesFromResource(R.xml.preferences)
        setPreferenceClickListeners()
    }

    private fun setPreferenceClickListeners() {
        val profilePreference = findPreference("profile")
//        profilePreference.onPreferenceClickListener({})

        profilePreference.onPreferenceClickListener = object : Preference.OnPreferenceClickListener {
            override fun onPreferenceClick(pref: Preference): Boolean {
                val intent = Intent(activity, ProfileActivity::class.java)
                startActivity(intent)

                return true
            }
        }
    }

}
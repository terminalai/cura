package app.nush.cura.ui.main

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import app.nush.cura.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}
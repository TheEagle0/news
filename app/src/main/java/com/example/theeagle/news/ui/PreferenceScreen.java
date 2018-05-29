package com.example.theeagle.news.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.example.theeagle.news.R;


public class PreferenceScreen extends android.preference.PreferenceFragment implements Preference.OnPreferenceChangeListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefrrence);
        Preference section = findPreference(getString(R.string.app_name));
        bindPreferenceSummaryToValue(section);

    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(preference.getContext());
        String preferenceString = preferences.getString(preference.getKey(), "");
        onPreferenceChange(preference, preferenceString);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        String stringValue = newValue.toString();
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                CharSequence[] labels = listPreference.getEntries();
                preference.setSummary(labels[prefIndex]);
            }
        } else {
            preference.setSummary(stringValue);
        }
        return true;
    }
}

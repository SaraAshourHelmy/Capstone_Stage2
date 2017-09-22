package com.education.capstone_stage2.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.view.activity.SplashActivity;


/**
 * Created by Sara on 6/15/2017.
 */

public class SettingFragment extends PreferenceFragmentCompat

        implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.setting_fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // PreferenceManager.getDefaultSharedPreferences(getContext())
        //       .registerOnSharedPreferenceChangeListener(this);
    }

   /* private void setPreferenceSummery(Preference preference) {

        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(listPreference.getValue());
            listPreference.setSummary(listPreference.getEntries()[index]);
        }
    }*/

    /*
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Preference preference = findPreference(key);
        if (preference != null) {

            setPreferenceSummery(preference);
        }
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        //getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this
        //);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {


    }
}

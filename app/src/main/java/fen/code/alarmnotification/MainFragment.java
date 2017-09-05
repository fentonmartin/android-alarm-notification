package fen.code.alarmnotification;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.Log;

import fen.code.alarmnotification.reminders.AlarmReceiver;

public class MainFragment extends PreferenceFragment {

    ListPreference listPreference;
    SharedPreferences preferences;
    SwitchPreference switchPreference;
    String currValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        preferences = PreferenceManager.getDefaultSharedPreferences
                (getActivity().getApplicationContext());

        switchPreference = (SwitchPreference) findPreference(getString(R.string.pref_key_reminder));
        Log.d("PREF onPreferenceClick", "SwitchPreference >> " + switchPreference.isChecked());

        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Log.d("PREF onPreferenceClick", "SwitchPreference >> " + switchPreference.isChecked());

                preferences.edit().putBoolean(getString(R.string.pref_key_reminder), switchPreference.isChecked()).apply();
                check();
                return true;
            }
        });

        listPreference = (ListPreference) findPreference(getString(R.string.pref_key_alarm));
        currValue = listPreference.getValue();

        Log.d("PREF onPreferenceClick", "ListPreference >>> " + currValue);
        listPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                currValue = listPreference.getValue();

                Log.d("PREF onPreferenceClick", "ListPreference >>> " + currValue);
                preferences.edit().putString(getString(R.string.pref_key_alarm), currValue).apply();
                check();
                return true;
            }
        });

        check();
    }

    private void check() {
        Log.d("PREF", "Shared Preferences - Remainder: " +
                preferences.getBoolean(getString(R.string.pref_key_reminder), false) + " : " +
                preferences.getString(getString(R.string.pref_key_alarm), "default"));

        // Add initiate scheduleAlarm
        AlarmReceiver.scheduleAlarm(getActivity().getApplicationContext());
    }
}

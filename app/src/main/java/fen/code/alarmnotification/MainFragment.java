package fen.code.alarmnotification;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.Log;
import android.widget.Toast;

import fen.code.alarmnotification.reminders.AlarmReceiver;

public class MainFragment extends PreferenceFragment {

    ListPreference listPreference;
    SharedPreferences preferences;
    SwitchPreference switchPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        preferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity().getApplicationContext());

        switchPreference = (SwitchPreference)
                findPreference(getString(R.string.pref_key_reminder));
        Log.d("PREF SWITCH", ">> " + switchPreference.isChecked());

        switchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Log.d("PREF SWITCH", ">> " + switchPreference.isChecked());

                preferences.edit().putBoolean(getString(R.string.pref_key_reminder),
                        switchPreference.isChecked()).apply();

                check();
                return true;
            }
        });

        listPreference = (ListPreference)
                findPreference(getString(R.string.pref_key_alarm));

        String currValue = listPreference.getValue();
        Toast.makeText(getActivity().getApplicationContext(),
                ">>> " + currValue, Toast.LENGTH_SHORT).show();
        Log.d("PREF onCreate", currValue);

        listPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                String currValue = listPreference.getValue();
                Toast.makeText(getActivity().getApplicationContext(),
                        ">>> " + currValue, Toast.LENGTH_SHORT).show();
                Log.d("PREF onPreferenceClick", currValue);

                preferences.edit().putString(getString(R.string.pref_key_alarm),
                        listPreference.getValue()).apply();

                check();
                return true;
            }
        });
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                String currValue = listPreference.getValue();
                Toast.makeText(getActivity().getApplicationContext(),
                        ">>> " + currValue, Toast.LENGTH_SHORT).show();
                Log.d("PREF onPreferenceChange", currValue);

                preferences.edit().putString(getString(R.string.pref_key_alarm),
                        listPreference.getValue()).apply();
                check();
                return true;
            }
        });

        check();
    }

    private void check() {
        Log.d("PREF ALL", preferences.getBoolean(getString(R.string.pref_key_reminder), false) +
                " " + preferences.getString(getString(R.string.pref_key_alarm), "12:00"));

        preferences.edit().putString(getString(R.string.pref_key_alarm),
                listPreference.getValue()).apply();

        Log.d("PREF ALL", preferences.getBoolean(getString(R.string.pref_key_reminder), false) +
                " " + preferences.getString(getString(R.string.pref_key_alarm), "12:00"));
    }
}

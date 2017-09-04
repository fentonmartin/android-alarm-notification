package fen.code.alarmnotification;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class MainFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}

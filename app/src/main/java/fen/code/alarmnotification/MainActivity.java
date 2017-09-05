package fen.code.alarmnotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import fen.code.alarmnotification.reminders.AlarmReceiver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MainFragment())
                .commit();
    }
}

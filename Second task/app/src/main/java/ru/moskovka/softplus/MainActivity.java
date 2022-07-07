package ru.moskovka.softplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "coldToastPrefs";
    private static final String LAUNCH_TIMES_KEY = "launchTimes";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        int timesLaunched = sharedPreferences.getInt(LAUNCH_TIMES_KEY, 0);
        editor.putInt(LAUNCH_TIMES_KEY, timesLaunched + 1);
        editor.commit();

        if (sharedPreferences.getInt(LAUNCH_TIMES_KEY, 0) == 3)
            Toast.makeText(this, "Приложение было открыто трижды", Toast.LENGTH_SHORT)
                    .show();
    }
}
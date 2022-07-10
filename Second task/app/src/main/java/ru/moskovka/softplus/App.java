package ru.moskovka.softplus;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class App extends Application {

    private static final String PREFERENCES_NAME = "coldToastPrefs";
    private static final String LAUNCH_TIMES_KEY = "launchTimes";

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        final boolean[] firstActivityCreated = {false};

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                if (firstActivityCreated[0])
                    return;

                firstActivityCreated[0] = true;
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity,
                                                    @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });

        new Handler().post(() -> {
            if (firstActivityCreated[0]){
                sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                int timesLaunched = sharedPreferences.getInt(LAUNCH_TIMES_KEY, 0);
                editor.putInt(LAUNCH_TIMES_KEY, timesLaunched + 1);
                editor.commit();

                if (sharedPreferences.getInt(LAUNCH_TIMES_KEY, 0) == 3)
                    Toast.makeText(this, "Приложение было открыто трижды",
                            Toast.LENGTH_SHORT)
                            .show();
            }
        });
    }
}

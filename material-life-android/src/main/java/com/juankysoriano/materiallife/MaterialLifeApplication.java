package com.juankysoriano.materiallife;

import android.app.Application;

import com.novoda.notils.logger.simple.Log;

public class MaterialLifeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextRetriever.INSTANCE.inject(this);

        Log.setShowLogs(BuildConfig.DEBUG);
    }
}

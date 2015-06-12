package com.juankysoriano.materiallife;

import com.novoda.notils.logger.simple.Log;

public class MaterialLifeApplication extends AnalyticsApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        ContextRetriever.INSTANCE.inject(this);

        Log.setShowLogs(BuildConfig.DEBUG);
    }
}

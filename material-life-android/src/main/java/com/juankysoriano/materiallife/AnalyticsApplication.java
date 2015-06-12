package com.juankysoriano.materiallife;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.juankysoriano.materiallife.analytics.AnalyticsTracker;

import io.fabric.sdk.android.Fabric;

public class AnalyticsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Crashlytics crashlytics = new Crashlytics.Builder().disabled(isAnalyticsDisabled()).build();
        Fabric.with(this, crashlytics);

        GoogleAnalytics analytics = GoogleAnalytics.getInstance(getApplicationContext());
        analytics.setAppOptOut(isAnalyticsDisabled());
        analytics.setDryRun(isAnalyticsDisabled());
        AnalyticsTracker.INSTANCE.inject(analytics.newTracker(R.xml.material_life_tracker));
    }

    private boolean isAnalyticsDisabled() {
        return BuildConfig.ANALYTICS_DISABLED;
    }
}

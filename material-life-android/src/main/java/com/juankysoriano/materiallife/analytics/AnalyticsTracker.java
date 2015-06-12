package com.juankysoriano.materiallife.analytics;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.juankysoriano.materiallife.activities.MaterialLifeActivity;

/**
 * TODO having AnalyticsTracker implementing ZenAnalytics interface then it is mockable so we should pass this as collaborator and test interactions.
 */
public enum AnalyticsTracker implements MaterialLifeAnalytics {
    INSTANCE;

    private Tracker tracker;

    public void inject(Tracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public void trackActivityStart(MaterialLifeActivity materialLifeActivity) {
        GoogleAnalytics.getInstance(materialLifeActivity).reportActivityStart(materialLifeActivity);
    }

    @Override
    public void trackActivityStop(MaterialLifeActivity materialLifeActivity) {
        GoogleAnalytics.getInstance(materialLifeActivity).reportActivityStop(materialLifeActivity);
    }
}


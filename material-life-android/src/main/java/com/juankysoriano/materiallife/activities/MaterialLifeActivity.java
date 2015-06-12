package com.juankysoriano.materiallife.activities;

import android.app.Activity;
import android.os.Bundle;

import com.juankysoriano.materiallife.ContextRetriever;
import com.juankysoriano.materiallife.analytics.AnalyticsTracker;

public class MaterialLifeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextRetriever.INSTANCE.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AnalyticsTracker.INSTANCE.trackActivityStart(this);
    }

    @Override
    protected void onStop() {
        AnalyticsTracker.INSTANCE.trackActivityStop(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        ContextRetriever.INSTANCE.inject((Activity) null);
        super.onDestroy();
    }
}

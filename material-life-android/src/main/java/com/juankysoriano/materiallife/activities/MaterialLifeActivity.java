package com.juankysoriano.materiallife.activities;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

import com.juankysoriano.materiallife.ContextRetriever;
import com.juankysoriano.materiallife.analytics.AnalyticsTracker;

public class MaterialLifeActivity extends FragmentActivity {

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
        ContextRetriever.INSTANCE.inject((FragmentActivity) null);
        super.onDestroy();
    }
}

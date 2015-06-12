package com.juankysoriano.materiallife.analytics;

import com.juankysoriano.materiallife.activities.MaterialLifeActivity;

public interface MaterialLifeAnalytics {

    void trackActivityStart(MaterialLifeActivity materialLifeActivity);

    void trackActivityStop(MaterialLifeActivity materialLifeActivity);
}

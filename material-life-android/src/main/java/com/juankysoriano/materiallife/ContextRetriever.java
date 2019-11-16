package com.juankysoriano.materiallife;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import androidx.fragment.app.FragmentActivity;

public enum ContextRetriever {
    INSTANCE;

    private Application application;
    private FragmentActivity activity;

    public void inject(Application application) {
        this.application = application;
    }

    public void inject(FragmentActivity activity) {
        this.activity = activity;
    }

    public Context getApplicationContext() {
        return application.getApplicationContext();
    }

    public FragmentActivity getActivity() {
        return activity;
    }

    public Resources getResources() {
        return application.getResources();
    }

}

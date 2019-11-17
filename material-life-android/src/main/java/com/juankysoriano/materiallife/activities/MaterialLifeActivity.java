package com.juankysoriano.materiallife.activities;

import android.os.Bundle;

import com.juankysoriano.materiallife.ContextRetriever;

import androidx.fragment.app.FragmentActivity;

public class MaterialLifeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextRetriever.INSTANCE.inject(this);
    }

    @Override
    protected void onDestroy() {
        ContextRetriever.INSTANCE.inject((FragmentActivity) null);
        super.onDestroy();
    }
}

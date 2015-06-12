package com.juankysoriano.materiallife.activities;

import android.os.Bundle;
import android.view.View;

import com.juankysoriano.materiallife.R;

public class GameOfLifeActivity extends MaterialLifeActivity implements View.OnAttachStateChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world);
    }

    @Override
    public void onViewAttachedToWindow(View v) {

    }

    @Override
    public void onViewDetachedFromWindow(View v) {

    }
}

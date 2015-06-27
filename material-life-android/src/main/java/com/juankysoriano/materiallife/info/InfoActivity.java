package com.juankysoriano.materiallife.info;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.juankysoriano.materiallife.activities.GameOfLifeActivity;

public class InfoActivity extends AppIntro {

    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(new FirstSlide(), getApplicationContext());
        addSlide(new FirstSlide(), getApplicationContext());
        addSlide(new FirstSlide(), getApplicationContext());
        addSlide(new FirstSlide(), getApplicationContext());

        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        showSkipButton(false);
    }

    @Override
    public void onSkipPressed() {
        // no-op
    }

    @Override
    public void onDonePressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), GameOfLifeActivity.class));
    }
}

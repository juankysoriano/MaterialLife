package com.juankysoriano.materiallife.info;

import android.content.Intent;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;
import com.juankysoriano.materiallife.activities.GameOfLifeActivity;

public class InfoActivity extends AppIntro2 {

    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(new WellcomeSlide(), getApplicationContext());
        addSlide(new PresentationSlide(), getApplicationContext());
        addSlide(new RulesSlide(), getApplicationContext());
        addSlide(new LifeCreationSlide(), getApplicationContext());
        setOffScreenPageLimit(2);
    }

    @Override
    public void onDonePressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), GameOfLifeActivity.class));
    }
}

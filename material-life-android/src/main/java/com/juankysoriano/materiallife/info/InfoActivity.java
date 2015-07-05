package com.juankysoriano.materiallife.info;

import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;
import com.juankysoriano.materiallife.info.preferences.InfoPreferences;
import com.juankysoriano.materiallife.info.slides.LifeCreationSlide;
import com.juankysoriano.materiallife.info.slides.PresentationSlide;
import com.juankysoriano.materiallife.info.slides.RulesSlide;
import com.juankysoriano.materiallife.info.slides.WellcomeSlide;

public class InfoActivity extends AppIntro2 {
    private static final int OFF_SCREEN_PAGE_LIMIT = 3;
    private final InfoPreferences infoPreferences;

    public InfoActivity() {
        super();
        infoPreferences = InfoPreferences.newInstance();
    }

    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(new WellcomeSlide(), getApplicationContext());
        addSlide(new PresentationSlide(), getApplicationContext());
        addSlide(new RulesSlide(), getApplicationContext());
        addSlide(new LifeCreationSlide(), getApplicationContext());
        setOffScreenPageLimit(OFF_SCREEN_PAGE_LIMIT);
    }

    @Override
    public void onDonePressed() {
        done();
    }

    private void done() {
        infoPreferences.markInfoAsSeen();
        finish();
    }
}

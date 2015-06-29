package com.juankysoriano.materiallife.info.slides;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.world.World;

public class PresentationSlide extends SlideFragmentWithWorld {

    @Override
    protected World instantiateWorld() {
        return World.newInstance();
    }

    @Override
    protected int getSlideLayoutId() {
        return R.layout.intro_presentation;
    }

    @Override
    protected int getWorldViewId() {
        return R.id.life_presentation_world;
    }
}

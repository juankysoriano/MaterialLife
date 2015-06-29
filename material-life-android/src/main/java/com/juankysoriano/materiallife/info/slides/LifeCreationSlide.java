package com.juankysoriano.materiallife.info.slides;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.world.EmptyWorld;
import com.juankysoriano.materiallife.world.World;

public class LifeCreationSlide extends SlideFragmentWithWorld {

    @Override
    protected World instantiateWorld() {
        return EmptyWorld.newInstance();
    }

    @Override
    protected int getSlideLayoutId() {
        return R.layout.intro_life_creation;
    }

    @Override
    protected int getWorldViewId() {
        return R.id.life_creation_world;
    }
}

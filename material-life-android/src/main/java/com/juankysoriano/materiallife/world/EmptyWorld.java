package com.juankysoriano.materiallife.world;

import com.juankysoriano.rainbow.core.drawing.RainbowDrawer;
import com.juankysoriano.rainbow.core.event.RainbowInputController;

import androidx.annotation.VisibleForTesting;

public class EmptyWorld extends World {

    public static EmptyWorld newInstance() {
        RainbowDrawer rainbowDrawer = new RainbowDrawer();
        RainbowInputController rainbowInputController = RainbowInputController.newInstance();
        return new EmptyWorld(rainbowDrawer, rainbowInputController);
    }

    @VisibleForTesting
    protected EmptyWorld(RainbowDrawer rainbowDrawer, RainbowInputController rainbowInputController) {
        super(rainbowDrawer, rainbowInputController);
    }

    @Override
    public void onSketchSetup() {
        super.onSketchSetup();
        clear();
    }
}

package com.juankysoriano.materiallife.world;

import android.net.Uri;

import com.juankysoriano.materiallife.world.life.GameOfLife;
import com.juankysoriano.rainbow.core.Rainbow;
import com.juankysoriano.rainbow.core.drawing.Modes;
import com.juankysoriano.rainbow.core.drawing.RainbowDrawer;
import com.juankysoriano.rainbow.core.event.RainbowInputController;
import com.juankysoriano.rainbow.core.graphics.RainbowImage;
import com.juankysoriano.rainbow.utils.schedulers.RainbowScheduler;
import com.juankysoriano.rainbow.utils.schedulers.RainbowSchedulers;

import androidx.annotation.VisibleForTesting;

public class World extends Rainbow implements RainbowImage.LoadPictureListener {
    private static final RainbowScheduler SCHEDULER = RainbowSchedulers.single("LoadWorld", RainbowSchedulers.Priority.MIN);

    @VisibleForTesting
    //CHECKSTYLE IGNORE VisibilityModifier
            GameOfLife gameOfLife;
    //CHECKSTYLE END IGNORE

    public static World newInstance() {
        RainbowDrawer rainbowDrawer = new RainbowDrawer();
        RainbowInputController rainbowInputController = RainbowInputController.newInstance();
        return new World(rainbowDrawer, rainbowInputController);
    }

    @VisibleForTesting
    protected World(RainbowDrawer rainbowDrawer, RainbowInputController rainbowInputController) {
        super(rainbowDrawer, rainbowInputController);
    }

    @Override
    public void onSketchSetup() {
        super.onSketchSetup();
        gameOfLife = GameOfLife.newInstance(getRainbowDrawer(), getRainbowInputController());
        getRainbowInputController().attach(gameOfLife);
    }

    public void startEdition() {
        gameOfLife.startEdition();
    }

    public void endEdition() {
        gameOfLife.endEdition();
    }

    public void clear() {
        gameOfLife.clear();
    }

    @Override
    public void onStep() {
        gameOfLife.doStep();
    }

    @Override
    public void onSketchDestroy() {
        getRainbowInputController().detach();
    }

    public void restoreLastWorld() {
        gameOfLife.restoreLastWorld();
    }

    public void loadWorldFrom(final Uri image) {
        gameOfLife.clear();
        SCHEDULER.scheduleNow(new Runnable() {
            @Override
            public void run() {
                getRainbowDrawer().loadImage(image, Modes.LoadMode.LOAD_CENTER_CROP, World.this);
            }
        });
    }

    @Override
    public void onLoadSucceed(RainbowImage image) {
        gameOfLife.loadWorldFrom(image);
    }

    @Override
    public void onLoadFail() {
        restoreLastWorld();
    }
}

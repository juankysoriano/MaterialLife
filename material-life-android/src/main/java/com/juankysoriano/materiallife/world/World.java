package com.juankysoriano.materiallife.world;

import android.net.Uri;
import android.support.annotation.VisibleForTesting;

import com.juankysoriano.materiallife.world.life.GameOfLife;
import com.juankysoriano.rainbow.core.Rainbow;
import com.juankysoriano.rainbow.core.drawing.RainbowDrawer;
import com.juankysoriano.rainbow.core.event.RainbowInputController;
import com.juankysoriano.rainbow.core.graphics.RainbowImage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class World extends Rainbow implements RainbowImage.LoadPictureListener {
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    @VisibleForTesting
    protected GameOfLife gameOfLife;

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
    public void onDrawingStep() {
        gameOfLife.doStep();
    }

    public void restoreLastWorld() {
        gameOfLife.restoreLastWorld();
    }

    public void loadWorldFrom(final Uri image) {
        gameOfLife.clear();
        EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                getRainbowDrawer().loadImage(image, RainbowImage.LOAD_CENTER_CROP, World.this);
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

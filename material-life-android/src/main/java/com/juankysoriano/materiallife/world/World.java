package com.juankysoriano.materiallife.world;

import com.juankysoriano.materiallife.world.performer.GameOfLife;
import com.juankysoriano.rainbow.core.Rainbow;
import com.juankysoriano.rainbow.core.drawing.RainbowDrawer;
import com.juankysoriano.rainbow.core.event.RainbowInputController;

public class World extends Rainbow {

    private GameOfLife gameOfLife;

    public static World newInstance() {
        RainbowDrawer rainbowDrawer = new RainbowDrawer();
        RainbowInputController rainbowInputController = RainbowInputController.newInstance();
        return new World(rainbowDrawer, rainbowInputController);
    }

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

    public void clear() {
        gameOfLife.clear();
    }

    public void endEdition() {
        gameOfLife.endEdition();
    }

    @Override
    public void onDrawingStep() {
        gameOfLife.doStep();
    }

    public void restoreLastWorld() {
        gameOfLife.restoreLastWorld();
    }
}

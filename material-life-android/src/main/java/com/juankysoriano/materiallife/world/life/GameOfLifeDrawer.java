package com.juankysoriano.materiallife.world.life;

import com.juankysoriano.materiallife.ContextRetriever;
import com.juankysoriano.materiallife.R;
import com.juankysoriano.rainbow.core.drawing.RainbowDrawer;
import com.juankysoriano.rainbow.core.graphics.RainbowImage;

import androidx.annotation.VisibleForTesting;

public class GameOfLifeDrawer {
    private static final int ALIVE_COLOR = ContextRetriever.INSTANCE.getResources().getColor(R.color.alive);
    private static final int DEAD_COLOR = ContextRetriever.INSTANCE.getResources().getColor(R.color.dead);
    private static final int SCALE_FACTOR = ContextRetriever.INSTANCE.getResources().getInteger(R.integer.cell_size);
    private static final int ALPHA = 70;
    private static final float OPAQUE = 255;
    private static final int ALIVE_CELL_THRESHOLD = 128;
    private static final int THREE = 3;
    private final RainbowDrawer rainbowDrawer;

    @VisibleForTesting
    GameOfLifeDrawer(RainbowDrawer rainbowDrawer) {
        this.rainbowDrawer = rainbowDrawer;
    }

    public static GameOfLifeDrawer newInstance(RainbowDrawer rainbowDrawer) {
        configure(rainbowDrawer);

        return new GameOfLifeDrawer(rainbowDrawer);
    }

    private static void configure(RainbowDrawer rainbowDrawer) {
        rainbowDrawer.noStroke();
        rainbowDrawer.smooth();
        rainbowDrawer.fill(ALIVE_COLOR);
    }

    int getCellStateFrom(RainbowImage image, int cellX, int cellY) {
        int color = image.get(cellX * SCALE_FACTOR, cellY * SCALE_FACTOR);
        int red = (int) rainbowDrawer.red(color);
        int green = (int) rainbowDrawer.green(color);
        int blue = (int) rainbowDrawer.blue(color);
        int grey = (red + green + blue) / THREE;

        return grey < ALIVE_CELL_THRESHOLD ? GameOfLife.DEAD : GameOfLife.ALIVE;
    }

    void paintCellAt(int x, int y) {
        rainbowDrawer.rect(x * SCALE_FACTOR, y * SCALE_FACTOR, SCALE_FACTOR, SCALE_FACTOR);
    }

    void paintBackground() {
        rainbowDrawer.background(DEAD_COLOR, ALPHA);
    }

    void clearBackground() {
        rainbowDrawer.background(DEAD_COLOR, OPAQUE);
    }
}

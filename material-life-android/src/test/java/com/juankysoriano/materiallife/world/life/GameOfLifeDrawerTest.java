package com.juankysoriano.materiallife.world.life;

import com.juankysoriano.materiallife.MaterialLifeTestBase;
import com.juankysoriano.rainbow.core.drawing.RainbowDrawer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


public class GameOfLifeDrawerTest extends MaterialLifeTestBase {
    private static final int ALPHA = 70;
    private static final float OPAQUE = 255;
    @Mock
    private RainbowDrawer rainbowDrawer;
    private GameOfLifeDrawer gameOfLifeDrawer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gameOfLifeDrawer = new GameOfLifeDrawer(rainbowDrawer);
    }

    @Test
    public void paintsCellsWithExpectedConfiguration() {
        gameOfLifeDrawer = GameOfLifeDrawer.newInstance(rainbowDrawer);

        verify(rainbowDrawer).smooth();
        verify(rainbowDrawer).noStroke();
        verify(rainbowDrawer).fill(ALIVE_COLOR);
    }

    @Test
    public void testThatPaintCellDrawsRectOnCellCoordinates() {
        gameOfLifeDrawer.paintCellAt(0, 0);

        verify(rainbowDrawer).rect(0, 0, SCALE_FACTOR, SCALE_FACTOR);
    }

    @Test
    public void testThatPaintBackgroundDrawsBackgroundWithTransparency() {
        gameOfLifeDrawer.paintBackground();

        verify(rainbowDrawer).background(DEAD_COLOR, ALPHA);
    }

    @Test
    public void testThatClearBackgroundDrawsBackgroundWithoutTransparency() {
        gameOfLifeDrawer.clearBackground();

        verify(rainbowDrawer).background(DEAD_COLOR, OPAQUE);
    }
}

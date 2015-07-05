package com.juankysoriano.materiallife.world.life;

import com.juankysoriano.materiallife.ContextRetriever;
import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.RobolectricMaterialLifeGradleTestRunner;
import com.juankysoriano.materiallife.MaterialLifeTestBase;
import com.juankysoriano.rainbow.core.drawing.RainbowDrawer;
import com.juankysoriano.rainbow.core.graphics.RainbowGraphics;
import com.juankysoriano.rainbow.core.graphics.RainbowImage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

@RunWith(RobolectricMaterialLifeGradleTestRunner.class)
public class GameOfLifeDrawerTest extends MaterialLifeTestBase {
    private static final int DEAD_COLOR = ContextRetriever.INSTANCE.getApplicationContext().getResources().getColor(R.color.dead);
    private static final int SCALE_FACTOR = ContextRetriever.INSTANCE.getApplicationContext().getResources().getInteger(R.integer.cell_size);
    private static final int ALPHA = 70;
    private static final float OPAQUE = 255;
    @Mock
    private RainbowGraphics rainbowGraphics;
    @Mock
    private RainbowImage rainbowImage;
    @Mock
    private RainbowDrawer rainbowDrawer;
    private GameOfLifeDrawer gameOfLifeDrawer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gameOfLifeDrawer = new GameOfLifeDrawer(rainbowDrawer);
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

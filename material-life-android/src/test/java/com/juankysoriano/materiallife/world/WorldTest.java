package com.juankysoriano.materiallife.world;

import android.net.Uri;

import com.juankysoriano.materiallife.MaterialLifeTestBase;
import com.juankysoriano.materiallife.world.life.GameOfLife;
import com.juankysoriano.rainbow.core.drawing.RainbowDrawer;
import com.juankysoriano.rainbow.core.event.RainbowInputController;
import com.juankysoriano.rainbow.core.graphics.RainbowImage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


public class WorldTest extends MaterialLifeTestBase {
    private static final Uri ANY_URI = null;
    private static final RainbowImage ANY_IMAGE = Mockito.mock(RainbowImage.class);
    @Mock
    private GameOfLife gameOfLifeMock;
    @Mock
    private RainbowDrawer rainbowDrawer;
    @Mock
    private RainbowInputController rainbowInputController;
    private WorldStub world;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        world = new WorldStub(rainbowDrawer, rainbowInputController);
        world.onSketchSetup();
    }

    @Test
    public void testThatWhenStartEditionIsCalledThenItDelegatesToGameOfLife() {
        world.startEdition();

        verify(gameOfLifeMock).startEdition();
    }

    @Test
    public void testThatWhenEndEditionIsCalledThenItDelegatesToGameOfLife() {
        world.endEdition();

        verify(gameOfLifeMock).endEdition();
    }

    @Test
    public void testThatWhenClearIsCalledThenItDelegatesToGameOfLife() {
        world.clear();

        verify(gameOfLifeMock).clear();
    }

    @Test
    public void testThatWhenOnDrawingStepIsCalledThenGameOfLifeStepIsDone() {
        world.onStep();

        verify(gameOfLifeMock).doStep();
    }

    @Test
    public void testThatWhenRestoreLastWorldIsCalledThenItDelegatesToGameOfLife() {
        world.restoreLastWorld();

        verify(gameOfLifeMock).restoreLastWorld();
    }

    @Test
    public void testThatWhenLoadFromIsCalledThenWorldIsCleared() {
        world.loadWorldFrom(ANY_URI);

        verify(gameOfLifeMock).clear();
    }

    @Test
    public void testThatOnLoadSuccessImageIsLoadedFromUri() {
        world.onLoadSucceed(ANY_IMAGE);

        verify(gameOfLifeMock).loadWorldFrom(ANY_IMAGE);
    }

    @Test
    public void testThatOnLoadFailThenLastWorldIsRestored() {
        world.onLoadFail();

        verify(gameOfLifeMock).restoreLastWorld();
    }

    private class WorldStub extends World {
        protected WorldStub(RainbowDrawer rainbowDrawer, RainbowInputController rainbowInputController) {
            super(rainbowDrawer, rainbowInputController);
        }

        @Override
        public void onSketchSetup() {
            gameOfLife = gameOfLifeMock;
        }
    }
}

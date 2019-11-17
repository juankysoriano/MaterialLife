package com.juankysoriano.materiallife.world.life;

import android.view.MotionEvent;

import com.juankysoriano.materiallife.MaterialLifeTestBase;
import com.juankysoriano.rainbow.core.drawing.RainbowDrawer;
import com.juankysoriano.rainbow.core.event.RainbowInputController;
import com.juankysoriano.rainbow.core.graphics.RainbowImage;
import com.openca.bi.discrete.AutomataDiscrete2D;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class GameOfLifeTest extends MaterialLifeTestBase {
    private static final int ANY_CELL_X = 0;
    private static final int ANY_CELL_Y = 0;
    private static final int AUTOMATA_WIDTH = 3;
    private static final int AUTOMATA_HEIGHT = 3;
    private static final int ALIVE = 1;
    private static final int DEAD = 0;
    @Mock
    private AutomataDiscrete2D gameOfLifeAutomata;
    @Mock
    private RainbowInputController rainbowInputController;
    @Mock
    private GameOfLifeDrawer gameOfLifeDrawer;
    @Mock
    private MotionEvent motionEvent;
    @Mock
    private RainbowDrawer rainbowDrawer;
    @Mock
    private RainbowImage rainbowImage;
    private GameOfLife gameOfLife;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(gameOfLifeAutomata.getWidth()).thenReturn(AUTOMATA_WIDTH);
        when(gameOfLifeAutomata.getHeight()).thenReturn(AUTOMATA_HEIGHT);
        when(rainbowInputController.getRainbowDrawer()).thenReturn(rainbowDrawer);
        gameOfLife = new GameOfLife(gameOfLifeAutomata, gameOfLifeDrawer, rainbowInputController);
        givenThatAutomataHasAllCellsDead();
    }

    @Test
    public void testThatWhenDoStepIsCalledThenBackgroundIsPainted() {
        gameOfLife.doStep();

        verify(gameOfLifeDrawer).paintBackground();
    }

    @Test
    public void testThatWhenDoStepIsCalledIfIsEditingAndAllCellsAliveThenAllCellsArePainted() {
        givenThatAutomataHasAllCellsAlive();
        givenThatIsEditing();

        gameOfLife.doStep();

        verify(gameOfLifeDrawer, times(AUTOMATA_WIDTH * AUTOMATA_HEIGHT)).paintCellAt(anyInt(), anyInt());
    }

    @Test
    public void testThatWhenDoStepIsCalledIfIsEditingAndAllCellsDeadThenNoCellsArePainted() {
        givenThatAutomataHasAllCellsDead();
        givenThatIsEditing();

        gameOfLife.doStep();

        verify(gameOfLifeDrawer, never()).paintCellAt(anyInt(), anyInt());
    }

    @Test
    public void testThatWhenDoStepIsCalledAndIsNotEditingThenEvolveIsCalledForGameOfLife() {
        gameOfLife.doStep();

        verify(gameOfLifeAutomata).evolve(gameOfLife);
    }

    @Test
    public void testThatWhenOnCellDetectedIsCalledTheCellIsPaintedIfItStateIsAlive() {
        gameOfLife.onCellDetected(ANY_CELL_X, ANY_CELL_Y, ALIVE);

        verify(gameOfLifeDrawer).paintCellAt(ANY_CELL_X, ANY_CELL_Y);
    }

    @Test
    public void testThatWhenOnCellDetectedIsCalledTheCellIsNotPaintedIfItStateIsDead() {
        gameOfLife.onCellDetected(ANY_CELL_X, ANY_CELL_Y, DEAD);

        verify(gameOfLifeDrawer, never()).paintCellAt(ANY_CELL_X, ANY_CELL_Y);
    }

    @Test
    public void testThatOnFingerDraggedExploresLineForCoordinatesInRainbowInputController() {
        givenThatHasPreviousFingerMovements();

        gameOfLife.onFingerDragged(motionEvent);

        verify(rainbowDrawer).exploreLine(rainbowInputController.getPreviousX(),
                rainbowInputController.getPreviousY(),
                rainbowInputController.getX(),
                rainbowInputController.getY(),
                RainbowDrawer.Precision.HIGH,
                gameOfLife);
    }

    @Test
    public void testThatWhenOnPointDetectedIsCalledThenTheDetectedCellIsAlive() {
        givenThatAutomataHasAllCellsDead();

        assertThat(gameOfLifeAutomata.getCells()[ANY_CELL_X][ANY_CELL_Y]).isEqualTo(DEAD);

        gameOfLife.onPointDetected(ANY_CELL_X, ANY_CELL_Y, ANY_CELL_X, ANY_CELL_Y);

        assertThat(gameOfLifeAutomata.getCells()[ANY_CELL_X][ANY_CELL_Y]).isEqualTo(ALIVE);
    }

    @Test
    public void testThatWhenStartEditionIsCalledThenIsEditingReturnsTrue() {
        gameOfLife.startEdition();

        assertThat(gameOfLife.isEditing()).isTrue();
    }

    @Test
    public void testThatWhenEditingIfEndEditionIsCalledThenIsEditingReturnsFalse() {
        gameOfLife.startEdition();
        gameOfLife.endEdition();

        assertThat(gameOfLife.isEditing()).isFalse();
    }

    @Test
    public void testThatWhenClearIsInvokedThenBackgroundIsCleared() {
        gameOfLife.clear();

        verify(gameOfLifeDrawer).clearBackground();
    }

    @Test
    public void testThatWhenClearIsInvokedThenDelegatesInAutomata() {
        gameOfLife.clear();

        verify(gameOfLifeAutomata).clear();
    }

    @Test
    public void testThatWhenLoadWorldFromIsCalledThenGameOfLifeDrawerCalculatesStateForAllCells() {
        gameOfLife.loadWorldFrom(rainbowImage);

        for (int i = 0; i < AUTOMATA_WIDTH; i++) {
            for (int j = 0; j < AUTOMATA_HEIGHT; j++) {
                verify(gameOfLifeDrawer).getCellStateFrom(rainbowImage, i, j);
            }
        }
    }

    private void givenThatIsEditing() {
        gameOfLife.startEdition();
    }

    private void givenThatAutomataHasAllCellsAlive() {
        int[][] cellsAlive = makeAllCellsAlive();
        when(gameOfLifeAutomata.getCells()).thenReturn(cellsAlive);
    }

    private void givenThatAutomataHasAllCellsDead() {
        int[][] cellsDead = makeAllCellsDead();
        when(gameOfLifeAutomata.getCells()).thenReturn(cellsDead);
    }

    private int[][] makeAllCellsAlive() {
        int[][] cellsAlive = new int[AUTOMATA_WIDTH][AUTOMATA_HEIGHT];
        fillCellsWith(cellsAlive, ALIVE);
        return cellsAlive;
    }

    private int[][] makeAllCellsDead() {
        int[][] cellsAlive = new int[AUTOMATA_WIDTH][AUTOMATA_HEIGHT];
        fillCellsWith(cellsAlive, DEAD);
        return cellsAlive;
    }

    private void fillCellsWith(int[][] cells, int state) {
        for (int i = 0; i < AUTOMATA_WIDTH; i++) {
            for (int j = 0; j < AUTOMATA_HEIGHT; j++) {
                cells[i][j] = state;
            }
        }
    }

    private void givenThatHasPreviousFingerMovements() {
        when(rainbowInputController.getX()).thenReturn(1f);
        when(rainbowInputController.getY()).thenReturn(1f);
        when(rainbowInputController.getPreviousX()).thenReturn(0f);
        when(rainbowInputController.getPreviousY()).thenReturn(0f);
    }
}

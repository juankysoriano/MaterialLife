package com.juankysoriano.materiallife.world.life;

import android.view.MotionEvent;

import com.juankysoriano.materiallife.ContextRetriever;
import com.juankysoriano.materiallife.R;
import com.juankysoriano.rainbow.core.drawing.RainbowDrawer;
import com.juankysoriano.rainbow.core.event.RainbowInputController;
import com.juankysoriano.rainbow.core.graphics.RainbowImage;
import com.openca.bi.OnCellUpdatedCallback2D;
import com.openca.bi.discrete.AutomataDiscrete2D;

import androidx.annotation.VisibleForTesting;

public class GameOfLife implements RainbowInputController.RainbowInteractionListener, OnCellUpdatedCallback2D, RainbowDrawer.PointDetectedListener {
    static final int ALIVE = 1;
    static final int DEAD = 0;
    private static final int CELL_SIZE = ContextRetriever.INSTANCE.getResources().getInteger(R.integer.cell_size);
    private final AutomataDiscrete2D gameOfLifeAutomata;
    private final RainbowInputController rainbowInputController;
    private final GameOfLifeDrawer gameOfLifeDrawer;
    private boolean editing;
    private int[][] cellsBackup;

    public static GameOfLife newInstance(RainbowDrawer rainbowDrawer,
                                         RainbowInputController rainbowInputController) {
        int width = rainbowDrawer.getWidth() / CELL_SIZE;
        int height = rainbowDrawer.getHeight() / CELL_SIZE;
        GameOfLifeCreator gameOfLifeCreator = GameOfLifeCreator.newInstance();
        AutomataDiscrete2D automata = gameOfLifeCreator.createGameOfLife(width, height);
        GameOfLifeDrawer gameOfLifeDrawer = GameOfLifeDrawer.newInstance(rainbowDrawer);

        return new GameOfLife(automata, gameOfLifeDrawer, rainbowInputController);
    }

    @VisibleForTesting
    GameOfLife(AutomataDiscrete2D gameOfLifeAutomata, GameOfLifeDrawer gameOfLifeDrawer, RainbowInputController rainbowInputController) {
        this.gameOfLifeAutomata = gameOfLifeAutomata;
        this.gameOfLifeDrawer = gameOfLifeDrawer;
        this.rainbowInputController = rainbowInputController;
    }

    public void doStep() {
        gameOfLifeDrawer.paintBackground();
        if (isEditing()) {
            paintCellsWithoutEvolution();
        } else {
            paintCellsAndEvolve();
        }
    }

    private void paintCellsWithoutEvolution() {
        for (int i = 0; i < gameOfLifeAutomata.getWidth(); i++) {
            for (int j = 0; j < gameOfLifeAutomata.getHeight(); j++) {
                paintCellWithStateAt(i, j, gameOfLifeAutomata.getCells()[i][j]);
            }
        }
    }

    private void paintCellsAndEvolve() {
        gameOfLifeAutomata.evolve(this);
    }

    @Override
    public void onCellDetected(int x, int y, int state) {
        paintCellWithStateAt(x, y, state);
    }

    private void paintCellWithStateAt(int x, int y, int state) {
        if (isCellAlive(state)) {
            gameOfLifeDrawer.paintCellAt(x, y);
        }
    }

    private boolean isCellAlive(int state) {
        return state == ALIVE;
    }

    @Override
    public void onSketchTouched(MotionEvent motionEvent) {
        onPointDetected(
                rainbowInputController.getPreviousX(),
                rainbowInputController.getPreviousY(),
                rainbowInputController.getX(),
                rainbowInputController.getY()
        );
    }

    @Override
    public void onSketchReleased(MotionEvent motionEvent) {
        //no-op
    }

    @Override
    public void onFingerDragged(MotionEvent motionEvent) {
        int x = (int) rainbowInputController.getX();
        int y = (int) rainbowInputController.getY();
        int previousX = (int) rainbowInputController.getPreviousX();
        int previousY = (int) rainbowInputController.getPreviousY();

        rainbowInputController.getRainbowDrawer().exploreLine(previousX, previousY, x, y, RainbowDrawer.Precision.HIGH, this);
    }

    @Override
    public void onPointDetected(float px, float py, float x, float y) {
        int cellX = (int) (x / CELL_SIZE);
        int cellY = (int) (y / CELL_SIZE);

        if (cellX >= 0 && cellX < gameOfLifeAutomata.getWidth()
                && cellY >= 0 && cellY < gameOfLifeAutomata.getHeight()) {
            gameOfLifeAutomata.getCells()[cellX][cellY] = ALIVE;
        }
    }

    @Override
    public void onMotionEvent(MotionEvent motionEvent) {
        //no-op
    }

    public void startEdition() {
        if (!isEditing()) {
            editing = true;
            doCellsBackup();
        }
    }

    @VisibleForTesting
    boolean isEditing() {
        return editing;
    }

    private void doCellsBackup() {
        cellsBackup = new int[gameOfLifeAutomata.getWidth()][];
        for (int i = 0; i < gameOfLifeAutomata.getWidth(); i++) {
            int[] row = gameOfLifeAutomata.getCells()[i];
            cellsBackup[i] = new int[row.length];
            System.arraycopy(row, 0, cellsBackup[i], 0, row.length);
        }
    }

    public void endEdition() {
        editing = false;
    }

    public void clear() {
        gameOfLifeDrawer.clearBackground();
        gameOfLifeAutomata.clear();
    }

    public void restoreLastWorld() {
        for (int i = 0; i < gameOfLifeAutomata.getWidth(); i++) {
            System.arraycopy(cellsBackup[i], 0, gameOfLifeAutomata.getCells()[i], 0, gameOfLifeAutomata.getHeight());
        }
    }

    public void loadWorldFrom(RainbowImage image) {
        for (int i = 0; i < gameOfLifeAutomata.getWidth(); i++) {
            for (int j = 0; j < gameOfLifeAutomata.getHeight(); j++) {
                gameOfLifeAutomata.getCells()[i][j] = gameOfLifeDrawer.getCellStateFrom(image, i, j);
            }
        }
    }
}

package com.juankysoriano.materiallife.world.performer;

import android.view.MotionEvent;

import com.juankysoriano.rainbow.core.drawing.RainbowDrawer;
import com.juankysoriano.rainbow.core.event.RainbowInputController;
import com.openca.Automata;
import com.openca.bi.OnCellUpdatedCallback2D;
import com.openca.bi.discrete.AutomataDiscrete2D;

public class GameOfLife implements RainbowInputController.RainbowInteractionListener, OnCellUpdatedCallback2D, RainbowDrawer.PointDetectedListener {
    private static final int ALIVE = 1;
    private static final int SCALE_FACTOR = 10;
    private static final int ALPHA = 70;
    private final AutomataDiscrete2D gameOfLife;
    private final RainbowDrawer rainbowDrawer;
    private final RainbowInputController rainbowInputController;
    private boolean editing;
    private int[][] cellsBackup;

    public static GameOfLife newInstance(RainbowDrawer rainbowDrawer, RainbowInputController rainbowInputController) {
        AutomataDiscrete2D automata = initAutomata(rainbowDrawer.getWidth() / SCALE_FACTOR, rainbowDrawer.getHeight() / SCALE_FACTOR);
        GameOfLife gameOfLife = new GameOfLife(automata, rainbowDrawer, rainbowInputController);
        rainbowInputController.setRainbowInteractionListener(gameOfLife);

        configure(rainbowDrawer);

        return new GameOfLife(automata, rainbowDrawer, rainbowInputController);
    }

    private static void configure(RainbowDrawer rainbowDrawer) {
        rainbowDrawer.noStroke();
        rainbowDrawer.smooth();
        rainbowDrawer.vSync();
        rainbowDrawer.fill(212, 1, 27);
    }

    private static AutomataDiscrete2D initAutomata(int width, int height) {
        Automata.Builder builder = new Automata.Builder();
        AutomataDiscrete2D automata = builder.width(width)
                .height(height)
                .radius(1)
                .states(2)
                .rule("8-24")
                .type(Automata.Type.OUTER_TOTALISTIC)
                .domain(Automata.Domain.DISCRETE)
                .dimension(Automata.Dimension.BIDIMENSIONAL)
                .build();
        automata.randomiseConfiguration();
        return automata;
    }

    protected GameOfLife(AutomataDiscrete2D gameOfLife, RainbowDrawer rainbowDrawer, RainbowInputController rainbowInputController) {
        this.gameOfLife = gameOfLife;
        this.rainbowDrawer = rainbowDrawer;
        this.rainbowInputController = rainbowInputController;
    }

    public void doStep() {
        paintBackground();
        if (editing) {
            paintCellsWithoutEvolution();
        } else {
            paintCellsAndEvolve();
        }
    }

    private void paintCellsWithoutEvolution() {
        for (int i = 0; i < gameOfLife.getWidth(); i++) {
            for (int j = 0; j < gameOfLife.getHeight(); j++) {
                onCellDetected(i, j, gameOfLife.getCells()[i][j]);
            }
        }
    }

    private void paintCellsAndEvolve() {
        gameOfLife.evolve(this);
    }

    @Override
    public void onCellDetected(int x, int y, int state) {
        if (isCellAlive(state)) {
            paintCellAt(x, y);
        }
    }

    private boolean isCellAlive(int state) {
        return state == ALIVE;
    }

    private void paintCellAt(int x, int y) {
        rainbowDrawer.rect(x * SCALE_FACTOR, y * SCALE_FACTOR, SCALE_FACTOR, SCALE_FACTOR);
    }

    private void paintBackground() {
        rainbowDrawer.background(35, 35, 50, ALPHA);
    }

    @Override
    public void onSketchTouched(MotionEvent motionEvent, RainbowDrawer rainbowDrawer) {
        onPointDetected(rainbowInputController.getPreviousSmoothX(),
                rainbowInputController.getPreviousSmoothY(),
                rainbowInputController.getSmoothX(),
                rainbowInputController.getSmoothY(), rainbowDrawer);
    }

    @Override
    public void onSketchReleased(MotionEvent motionEvent, RainbowDrawer rainbowDrawer) {

    }

    @Override
    public void onFingerDragged(MotionEvent motionEvent, RainbowDrawer rainbowDrawer) {
        int x = (int) rainbowInputController.getSmoothX();
        int y = (int) rainbowInputController.getSmoothY();
        int previousX = (int) rainbowInputController.getPreviousSmoothX();
        int previousY = (int) rainbowInputController.getPreviousSmoothY();

        rainbowDrawer.exploreLine(previousX, previousY, x, y, RainbowDrawer.Precision.HIGH, this);
    }

    @Override
    public void onPointDetected(float px, float py, float x, float y, RainbowDrawer rainbowDrawer) {
        if (x >= 0 && x < rainbowDrawer.getWidth()
                && y > 0 && y < rainbowDrawer.getHeight()) {
            gameOfLife.getCells()[((int) x / SCALE_FACTOR)][((int) y / SCALE_FACTOR)] = ALIVE;
        }
    }

    @Override
    public void onMotionEvent(MotionEvent motionEvent, RainbowDrawer rainbowDrawer) {

    }

    public void startEdition() {
        editing = true;
        doCellsBackup();
    }

    private void doCellsBackup() {
        cellsBackup = new int[gameOfLife.getWidth()][];
        for (int i = 0; i < gameOfLife.getWidth(); i++) {
            int[] row = gameOfLife.getCells()[i];
            cellsBackup[i] = new int[row.length];
            System.arraycopy(row, 0, cellsBackup[i], 0, row.length);
        }
    }

    public void endEdition() {
        editing = false;
    }

    public void clear() {
        for (int i = 0; i < gameOfLife.getWidth(); i++) {
            for (int j = 0; j < gameOfLife.getHeight(); j++) {
                gameOfLife.getCells()[i][j] = 0;
            }
        }
    }

    public void restoreLastWorld() {
        for (int i = 0; i < gameOfLife.getWidth(); i++) {
            System.arraycopy(cellsBackup[i], 0, gameOfLife.getCells()[i], 0, gameOfLife.getHeight());
        }
    }
}

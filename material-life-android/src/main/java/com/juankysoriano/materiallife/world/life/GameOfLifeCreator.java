package com.juankysoriano.materiallife.world.life;

import com.openca.Automata;
import com.openca.bi.discrete.AutomataDiscrete2D;

public class GameOfLifeCreator {

    private static final String GAME_OF_LIFE_RULE = "8-24";
    private static final int GAME_OF_LIFE_RADIUS = 1;
    private static final int GAME_OF_LIFE_STATES = 2;

    private final Automata.Builder builder;

    protected GameOfLifeCreator(Automata.Builder builder) {
        this.builder = builder;
    }

    public static GameOfLifeCreator newInstance() {
        return new GameOfLifeCreator(new Automata.Builder());
    }

    public AutomataDiscrete2D createGameOfLife(int width, int height) {
        AutomataDiscrete2D automata = builder.width(width)
                .height(height)
                .radius(GAME_OF_LIFE_RADIUS)
                .states(GAME_OF_LIFE_STATES)
                .rule(GAME_OF_LIFE_RULE)
                .type(Automata.Type.OUTER_TOTALISTIC)
                .domain(Automata.Domain.DISCRETE)
                .dimension(Automata.Dimension.BIDIMENSIONAL)
                .build();

        automata.randomiseConfiguration();
        return automata;
    }
}

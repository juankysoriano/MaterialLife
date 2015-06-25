package com.juankysoriano.materiallife.editor;

import com.juankysoriano.materiallife.world.World;

public class WorldEditor {
    private final World world;

    public WorldEditor(World world) {
        this.world = world;
    }

    public void startEdition() {
        world.startEdition();
    }

    public void clear() {
        world.clear();
    }

    public void cancel() {
        world.restoreLastWorld();
    }

    public void endEdition() {
        world.endEdition();
    }
}

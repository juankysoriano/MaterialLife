package com.juankysoriano.materiallife.editor;

import android.net.Uri;

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
        endEdition();
    }

    public void endEdition() {
        world.endEdition();
    }

    public void loadWorldFrom(Uri image) {
        world.loadWorldFrom(image);
    }
}

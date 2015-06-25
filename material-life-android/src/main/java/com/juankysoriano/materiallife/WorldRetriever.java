package com.juankysoriano.materiallife;

import com.juankysoriano.materiallife.world.World;

public enum WorldRetriever {
    INSTANCE;

    private World world;

    public void inject(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

}

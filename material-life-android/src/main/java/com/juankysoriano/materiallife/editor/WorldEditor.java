package com.juankysoriano.materiallife.editor;

import android.net.Uri;
import androidx.annotation.VisibleForTesting;

import com.juankysoriano.materiallife.WorldRetriever;
import com.juankysoriano.materiallife.world.World;

public class WorldEditor {
    private final World world;

    public static WorldEditor newInstance() {
        return new WorldEditor(WorldRetriever.INSTANCE.getWorld());
    }

    @VisibleForTesting
    protected WorldEditor(World world) {
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

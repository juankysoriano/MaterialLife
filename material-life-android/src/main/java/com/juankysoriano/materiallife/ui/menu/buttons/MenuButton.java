package com.juankysoriano.materiallife.ui.menu.buttons;

import android.support.annotation.IdRes;

public enum MenuButton {
    PLAY(1),
    CLEAR(2),
    RAMDOMISE(3),
    SETTINGS(4),
    MENU(5);

    private final int id;

    MenuButton(@IdRes int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

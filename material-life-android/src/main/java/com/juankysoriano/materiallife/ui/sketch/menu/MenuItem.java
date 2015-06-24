package com.juankysoriano.materiallife.ui.sketch.menu;

public enum MenuItem {
    EDIT_WORLD("Edit World"),
    LOAD_WORLD("Load World"),
    SETTINGS("Settings"),
    INFO("Info");

    String value;

    MenuItem(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public MenuItem getFrom(int value) {
        return value < values().length ? values()[value] : EDIT_WORLD;
    }
}

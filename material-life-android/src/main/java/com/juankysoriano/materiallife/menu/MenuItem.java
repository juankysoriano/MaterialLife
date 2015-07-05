package com.juankysoriano.materiallife.menu;

public enum MenuItem {
    EDIT_WORLD("Edit World"),
    LOAD_WORLD("Load World"),
    INFO("Info");

    private final String value;

    MenuItem(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

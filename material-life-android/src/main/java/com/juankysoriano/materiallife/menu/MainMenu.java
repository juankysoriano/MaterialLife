package com.juankysoriano.materiallife.menu;

import com.juankysoriano.materiallife.ui.sketch.menu.MenuOptionsView;

public interface MainMenu {
    void attachItemSelectedListener(MenuOptionsView.OnItemSelectedListener listener);

    void detachItemSelectedListener();

    boolean isOpened();

    void closeMenu();

    void openMenu();

    MainMenuFragment getFragment();
}

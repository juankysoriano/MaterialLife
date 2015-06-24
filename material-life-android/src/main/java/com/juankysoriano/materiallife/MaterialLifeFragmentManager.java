package com.juankysoriano.materiallife;

import com.juankysoriano.materiallife.menus.MenuFragment;

public class MaterialLifeFragmentManager {

    public MenuFragment addMenuFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.menu_container, menuFragment).commit();
    };
}

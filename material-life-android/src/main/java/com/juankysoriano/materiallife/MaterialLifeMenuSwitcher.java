package com.juankysoriano.materiallife;

import android.support.v4.app.FragmentManager;

import com.juankysoriano.materiallife.editor.WorldEditorMenu;
import com.juankysoriano.materiallife.editor.WorldEditorMenuFragment;
import com.juankysoriano.materiallife.menu.MainMenu;
import com.juankysoriano.materiallife.menu.MainMenuFragment;
import com.novoda.notils.caster.Classes;

public class MaterialLifeMenuSwitcher {

    private final FragmentManager fragmentManager;

    public MaterialLifeMenuSwitcher(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public MainMenu addMainMenu() {
        if (mainMenuIsPresent()) {
            return Classes.from(fragmentManager.findFragmentByTag(MainMenuFragment.TAG));
        } else {
            MainMenuFragment mainMenu = new MainMenuFragment();
            fragmentManager.beginTransaction().replace(R.id.menu_container, mainMenu, MainMenuFragment.TAG).commit();

            return mainMenu;
        }
    }

    public WorldEditorMenu addEditorMenu() {
        if (editorMenuIsPresent()) {
            return Classes.from(fragmentManager.findFragmentByTag(WorldEditorMenuFragment.TAG));
        } else {
            WorldEditorMenuFragment worldEditorMenu = new WorldEditorMenuFragment();
            fragmentManager.beginTransaction().replace(R.id.menu_container, worldEditorMenu, WorldEditorMenuFragment.TAG).commit();

            return worldEditorMenu;
        }
    }

    private boolean mainMenuIsPresent() {
        return fragmentManager.findFragmentByTag(MainMenuFragment.TAG) != null;
    }

    private boolean editorMenuIsPresent() {
        return fragmentManager.findFragmentByTag(WorldEditorMenuFragment.TAG) != null;
    }
}

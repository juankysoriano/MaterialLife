package com.juankysoriano.materiallife;

import android.support.v4.app.FragmentManager;

import com.juankysoriano.materiallife.editor.WorldEditorMenu;
import com.juankysoriano.materiallife.editor.WorldEditorMenuFragment;
import com.juankysoriano.materiallife.imageloader.ImageLoader;
import com.juankysoriano.materiallife.imageloader.ImageLoaderFragment;
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
            MainMenu mainMenu = new MainMenuFragment();
            fragmentManager.beginTransaction().replace(R.id.menu_container, mainMenu.getFragment(), MainMenuFragment.TAG).commit();

            return mainMenu;
        }
    }

    public WorldEditorMenu addEditorMenu() {
        if (editorMenuIsPresent()) {
            return Classes.from(fragmentManager.findFragmentByTag(WorldEditorMenuFragment.TAG));
        } else {
            WorldEditorMenu worldEditorMenu = new WorldEditorMenuFragment();
            fragmentManager.beginTransaction().replace(R.id.menu_container, worldEditorMenu.getFragment(), WorldEditorMenuFragment.TAG).commit();

            return worldEditorMenu;
        }
    }

    public ImageLoader addImageLoader() {
        if (imageLoaderIsPresent()) {
            return Classes.from(fragmentManager.findFragmentByTag(ImageLoaderFragment.TAG));
        } else {
            ImageLoader imageLoader = new ImageLoaderFragment();
            fragmentManager.beginTransaction().replace(R.id.menu_container, imageLoader.getFragment(), ImageLoaderFragment.TAG).commit();

            return imageLoader;
        }
    }

    private boolean mainMenuIsPresent() {
        return fragmentManager.findFragmentByTag(MainMenuFragment.TAG) != null;
    }

    private boolean editorMenuIsPresent() {
        return fragmentManager.findFragmentByTag(WorldEditorMenuFragment.TAG) != null;
    }

    private boolean imageLoaderIsPresent() {
        return fragmentManager.findFragmentByTag(ImageLoaderFragment.TAG) != null;
    }
}

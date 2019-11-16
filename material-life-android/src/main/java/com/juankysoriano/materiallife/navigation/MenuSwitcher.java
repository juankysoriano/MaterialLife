package com.juankysoriano.materiallife.navigation;

import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentManager;

import com.juankysoriano.materiallife.ContextRetriever;
import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.editor.WorldEditorMenu;
import com.juankysoriano.materiallife.editor.WorldEditorMenuFragment;
import com.juankysoriano.materiallife.imageloader.ImageLoader;
import com.juankysoriano.materiallife.imageloader.ImageLoaderFragment;
import com.juankysoriano.materiallife.menu.MainMenu;
import com.juankysoriano.materiallife.menu.MainMenuFragment;
import com.novoda.notils.caster.Classes;

public class MenuSwitcher {

    private final FragmentManager fragmentManager;

    public static MenuSwitcher newInstance() {
        return new MenuSwitcher(ContextRetriever.INSTANCE.getActivity().getSupportFragmentManager());
    }

    @VisibleForTesting
    protected MenuSwitcher(FragmentManager fragmentManager) {
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

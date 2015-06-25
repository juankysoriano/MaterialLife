package com.juankysoriano.materiallife.activities;

import android.os.Bundle;
import android.view.ViewGroup;

import com.juankysoriano.materiallife.MaterialLifeMenuSwitcher;
import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.WorldRetriever;
import com.juankysoriano.materiallife.editor.EditorAction;
import com.juankysoriano.materiallife.editor.WorldEditorMenu;
import com.juankysoriano.materiallife.menu.MainMenu;
import com.juankysoriano.materiallife.menu.MenuItem;
import com.juankysoriano.materiallife.ui.sketch.editor.EditorMenuView;
import com.juankysoriano.materiallife.ui.sketch.menu.MenuOptionsView;
import com.juankysoriano.materiallife.world.World;

public class GameOfLifeActivity extends MaterialLifeActivity {

    private final World world;
    private MaterialLifeMenuSwitcher materialLifeMenuSwitcher;
    private MainMenu mainMenu;
    private WorldEditorMenu editorMenu;

    public GameOfLifeActivity() {
        this(World.newInstance());
    }

    public GameOfLifeActivity(World world) {
        WorldRetriever.INSTANCE.inject(world);
        this.world = world;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        materialLifeMenuSwitcher = new MaterialLifeMenuSwitcher(getSupportFragmentManager());

        setContentView(R.layout.world);
        world.injectInto((ViewGroup) findViewById(R.id.world));

        addMainMenu();
    }

    @Override
    protected void onStart() {
        super.onStart();
        world.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        world.resume();
    }

    private MenuOptionsView.OnItemSelectedListener onMenuItemSelectedListener = new MenuOptionsView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(MenuItem item) {
            switch (item) {
                case EDIT_WORLD:
                    addEditorMenu();
                    break;
            }
        }
    };

    private EditorMenuView.OnActionSelectedListener onActionSelectedListener = new EditorMenuView.OnActionSelectedListener() {
        @Override
        public void onActionPerformed(EditorAction item) {
            switch (item) {
                case CANCEL:
                case DONE:
                    addMainMenu();
                    break;
            }
        }
    };

    private void addMainMenu() {
        mainMenu = materialLifeMenuSwitcher.addMainMenu();
        mainMenu.attachItemSelectedListener(onMenuItemSelectedListener);
        removeEditorMenu();
    }

    private void removeEditorMenu() {
        if (hasEditorMenu()) {
            editorMenu.detachActionSelectedListener();
            editorMenu = null;
        }
    }

    private void addEditorMenu() {
        editorMenu = materialLifeMenuSwitcher.addEditorMenu();
        editorMenu.attachActionSelectedListener(onActionSelectedListener);
        removeMainMenu();
    }

    private void removeMainMenu() {
        if (hasMainMenu()) {
            mainMenu.detachItemSelectedListener();
            mainMenu = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (hasMainMenu() && mainMenu.isOpened()) {
            mainMenu.closeMenu();
        } else if (hasEditorMenu()) {
            addMainMenu();
        } else {
            super.onBackPressed();
        }
    }

    private boolean hasMainMenu() {
        return mainMenu != null;
    }

    private boolean hasEditorMenu() {
        return editorMenu != null;
    }

    @Override
    protected void onPause() {
        world.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        world.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        world.destroy();
        super.onDestroy();
    }
}

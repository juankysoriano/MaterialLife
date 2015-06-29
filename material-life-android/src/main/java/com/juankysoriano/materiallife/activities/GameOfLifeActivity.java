package com.juankysoriano.materiallife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import com.juankysoriano.materiallife.MaterialLifeMenuSwitcher;
import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.WorldRetriever;
import com.juankysoriano.materiallife.activities.navigator.MaterialLifePictureRetriever;
import com.juankysoriano.materiallife.editor.EditorAction;
import com.juankysoriano.materiallife.editor.WorldEditorMenu;
import com.juankysoriano.materiallife.imageloader.ImageLoader;
import com.juankysoriano.materiallife.imageloader.ImageLoaderAction;
import com.juankysoriano.materiallife.info.InfoActivity;
import com.juankysoriano.materiallife.info.preferences.InfoPreferences;
import com.juankysoriano.materiallife.menu.MainMenu;
import com.juankysoriano.materiallife.menu.MenuItem;
import com.juankysoriano.materiallife.ui.sketch.editor.EditorMenuView;
import com.juankysoriano.materiallife.ui.sketch.menu.MenuOptionsView;
import com.juankysoriano.materiallife.world.World;

public class GameOfLifeActivity extends MaterialLifeActivity {

    private final World world;
    private MaterialLifeMenuSwitcher materialLifeMenuSwitcher;
    private MaterialLifePictureRetriever materialLifePictureRetriever;
    private MainMenu mainMenu;
    private WorldEditorMenu editorMenu;
    private ImageLoader imageLoader;

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
        materialLifePictureRetriever = new MaterialLifePictureRetriever();

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
                case LOAD_WORLD:
                    addImageLoader();
                    break;
                case INFO:
                    Intent launchIntent = new Intent(getApplicationContext(), InfoActivity.class);
                    launchIntent.putExtra(InfoPreferences.FROM_MENU, true);
                    startActivity(launchIntent);
                    break;
            }
        }
    };

    private EditorMenuView.OnActionSelectedListener onActionSelectedListener = new EditorMenuView.OnActionSelectedListener() {
        @Override
        public void onActionPerformed(EditorAction action) {
            switch (action) {
                case DONE:
                case CANCEL:
                    addMainMenu();
                    break;
            }
        }
    };

    private ImageLoader.OnLoadImageSelectedListener onLoadImageSelectedListener = new ImageLoader.OnLoadImageSelectedListener() {
        @Override
        public void onLoadImage(ImageLoaderAction action) {
            switch (action) {
                case CAMERA:
                    materialLifePictureRetriever.openCameraForResult();
                    break;
                case GALLERY:
                    materialLifePictureRetriever.openGalleryForResult();
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageLoaderResult imageLoaderResult = ImageLoaderResult.from(requestCode);

        if (resultCode != RESULT_OK) {
            imageLoader.abortPictureLoading();
            addMainMenu();
            return;
        }

        switch (imageLoaderResult) {
            case CAMERA:
                imageLoader.loadWorldFrom(materialLifePictureRetriever.getLastLoadedImagePath());
                addEditorMenu();
                break;
            case GALLERY:
                imageLoader.loadWorldFrom(data.getData());
                addEditorMenu();
                break;
        }
    }

    private void addMainMenu() {
        mainMenu = materialLifeMenuSwitcher.addMainMenu();
        mainMenu.attachItemSelectedListener(onMenuItemSelectedListener);
        removeEditorMenu();
        removeImageLoader();
    }

    private void removeMainMenu() {
        if (hasMainMenu()) {
            mainMenu.detachItemSelectedListener();
            mainMenu = null;
        }
    }

    private void addImageLoader() {
        imageLoader = materialLifeMenuSwitcher.addImageLoader();
        imageLoader.attachLoadImageListener(onLoadImageSelectedListener);
        removeMainMenu();
    }

    private void removeImageLoader() {
        if (hasImageLoader()) {
            imageLoader.detachActionSelectedListener();
            imageLoader = null;
        }
    }

    private void addEditorMenu() {
        editorMenu = materialLifeMenuSwitcher.addEditorMenu();
        editorMenu.attachActionSelectedListener(onActionSelectedListener);
        removeMainMenu();
    }

    private void removeEditorMenu() {
        if (hasEditorMenu()) {
            editorMenu.detachActionSelectedListener();
            editorMenu = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (hasMainMenu() && mainMenu.isOpened()) {
            mainMenu.closeMenu();
        } else if (hasEditorMenu()) {
            editorMenu.abortEdition();
            addMainMenu();
        } else if (hasImageLoader()) {
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

    private boolean hasImageLoader() {
        return imageLoader != null;
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

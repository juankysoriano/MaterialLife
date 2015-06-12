package com.juankysoriano.materiallife.ui.menu;

import com.juankysoriano.materiallife.ui.menu.buttons.FloatingActionButton;
import com.juankysoriano.materiallife.ui.menu.buttons.MenuButton;
import com.juankysoriano.materiallife.ui.sketch.WorldView;
import com.oguzdev.circularfloatingactionmenu.library.CircularMenu;


public class MaterialLifeMenu implements CircularMenu.MenuStateChangeListener {//, ZenSketch.PaintListener {

    private final WorldView worldView;
    private boolean isPainting;

    protected MaterialLifeMenu(WorldView worldView) {
        this.worldView = worldView;
    }

    public static MaterialLifeMenu newInstance(WorldView worldView) {
        return new MaterialLifeMenu(worldView);
    }

    public void toggle() {
        getCircularMenu().toggle(true);
        getMenuButton().rotate();
    }

    private FloatingActionButton getMenuButton() {
        return getButtonViewFor(MenuButton.MENU);
    }

    private CircularMenu getCircularMenu() {
        return worldView.getCircularMenu();
    }

    public FloatingActionButton getButtonViewFor(MenuButton menuButton) {
        switch (menuButton) {
            case MENU:
                return (FloatingActionButton) getCircularMenu().getActionView();
            default:
                return (FloatingActionButton) getCircularMenu().findSubActionViewWithId(menuButton.getId());
        }
    }

    private void hide() {
        if (getCircularMenu().isOpen()) {
            getCircularMenu().toggle(true);
            getMenuButton().rotate();
        } else {
            getMenuButton().hide();
        }
    }

    private void show() {
        getMenuButton().show();
    }

    @Override
    public void onMenuOpened(CircularMenu circularMenu) {
        if (isPainting) {
            hide();
        }
    }

    @Override
    public void onMenuClosed(CircularMenu circularMenu) {
        if (isPainting) {
            getMenuButton().hide();
        }
    }

/*    @Override
    public void onPaintingStart() {
        isPainting = true;
        worldView.post(new Runnable() {
            @Override
            public void run() {
                hide();
            }
        });
    }

    @Override
    public void onPaintingEnd() {
        isPainting = false;
        worldView.post(new Runnable() {
            @Override
            public void run() {
                show();
            }
        });
    }*/
}

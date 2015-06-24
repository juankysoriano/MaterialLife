package com.juankysoriano.materiallife.world.actions;

import com.juankysoriano.materiallife.ContextRetriever;
import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.ui.sketch.editor.EditorView;
import com.juankysoriano.materiallife.ui.sketch.menu.MenuView;
import com.juankysoriano.materiallife.world.World;
import com.novoda.notils.caster.Views;

public class WorldEditor implements EditorView.EditionListener {
    private final World world;
    private final EditorView editorView;
    private final MenuView menuView;

    public static WorldEditor newInstance(World world) {
        EditorView editorView = Views.findById(ContextRetriever.INSTANCE.getActivity(), R.id.edit_layout);
        MenuView menuView = Views.findById(ContextRetriever.INSTANCE.getActivity(), R.id.menu_layout);
        WorldEditor worldEditor = new WorldEditor(world, editorView, menuView);
        editorView.setEditionListener(worldEditor);
        return worldEditor;
    }

    protected WorldEditor(World world, EditorView editorView, MenuView menuView) {
        this.world = world;
        this.editorView = editorView;
        this.menuView = menuView;
    }

    public void startEdition() {
        menuView.closeMenu();
        editorView.show();
        menuView.hide();

        world.startEdition();
    }

    @Override
    public void onAccept() {
        editorView.hide();
        menuView.show();

        world.endEdition();
    }

    @Override
    public void onClear() {
        world.clear();
    }

    @Override
    public void onCancel() {
        editorView.hide();
        menuView.show();

        world.restoreLastWorld();
        world.endEdition();
    }
}

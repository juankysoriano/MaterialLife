package com.juankysoriano.materiallife.editor;

import com.juankysoriano.materiallife.ui.sketch.editor.EditorMenuView;

public interface WorldEditorMenu {
    void attachActionSelectedListener(EditorMenuView.OnActionSelectedListener listener);

    void detachActionSelectedListener();

    WorldEditorMenuFragment getFragment();
}

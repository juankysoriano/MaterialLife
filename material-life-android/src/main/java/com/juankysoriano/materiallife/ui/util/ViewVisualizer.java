package com.juankysoriano.materiallife.ui.util;

import android.view.View;

public abstract class ViewVisualizer {

    private static final float OPAQUE = 1f;
    private static final float TRANSPARENT = 0f;
    private static final float NONE = 0f;
    private static final float FULL = 1f;

    public static void makeOpaque(View view) {
        view.setAlpha(OPAQUE);
    }

    public static void makeTransparent(View view) {
        view.setAlpha(TRANSPARENT);
    }

    public static void show(View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static void hide(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    public static void makeSmall(View view) {
        view.setScaleX(NONE);
        view.setScaleY(NONE);
    }

    public static void makeBig(View view) {
        view.setScaleX(FULL);
        view.setScaleY(FULL);
    }
}

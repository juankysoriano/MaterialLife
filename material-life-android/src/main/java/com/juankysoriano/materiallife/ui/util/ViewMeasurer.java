package com.juankysoriano.materiallife.ui.util;

import android.graphics.Point;
import android.view.View;

import com.juankysoriano.materiallife.ui.sketch.menu.NormalisedCenter;

/**
 * This view measurer asumes that the view has been already inflated and displayed
 */
public abstract class ViewMeasurer {

    public static Point getViewCenter(View view) {
        int centerX = (int) (view.getX() + view.getWidth() / 2);
        int centerY = (int) (view.getY() + view.getHeight() / 2);

        return new Point(centerX, centerY);
    }

    /**
     * @param view The view to measure.
     * @return the view normalised center respect its parent
     */
    public static NormalisedCenter getViewNormalisedCenter(View view, View parent) {
        float normalisedCenterX = (view.getX() + view.getWidth() / 2) / parent.getWidth();
        float normalisedCenterY = (view.getY() + view.getHeight() / 2) / parent.getHeight();

        return new NormalisedCenter(normalisedCenterX, normalisedCenterY);
    }

    public static int getRadiusFor(View view) {
        return (int) Math.sqrt(Math.pow(view.getWidth()/2, 2) + Math.pow(view.getHeight()/2, 2));
    }

    private static View parent(View view) {
        return (View) view.getParent();
    }

}

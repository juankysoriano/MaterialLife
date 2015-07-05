package com.juankysoriano.materiallife.ui.util;

import android.graphics.Point;
import android.view.View;

/**
 * This view measurer asumes that the view has been already inflated and displayed
 */
public class ViewMeasurer {

    private ViewMeasurer() {
        //no-op
    }

    public static Point getViewCenter(View view) {
        int centerX = view.getLeft() + view.getWidth() / 2;
        int centerY = view.getTop() + view.getHeight() / 2;

        return new Point(centerX, centerY);
    }

    public static Point getViewCenterOnScreen(View view) {
        int[] location = new int[2];

        view.getLocationOnScreen(location);

        int centerX = location[0] + view.getWidth() / 2;
        int centerY = location[1] + view.getHeight() / 2;

        return new Point(centerX, centerY);
    }
    public static int getRadiusFrom(Point point, View view) {
        int hypotenuseA = Math.max(point.x, view.getWidth() - point.x);
        int hypotenuseB = Math.max(point.y, view.getHeight() - point.y);

        return (int) Math.sqrt(Math.pow(hypotenuseA, 2) + Math.pow(hypotenuseB, 2));
    }
}

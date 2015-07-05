package com.juankysoriano.materiallife.ui.sketch.reveal;

import android.animation.AnimatorListenerAdapter;
import android.graphics.Point;
import android.view.View;

import com.juankysoriano.materiallife.ui.util.ViewMeasurer;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class ViewRevealer {
    private static final int ANIMATION_DURATION = 300;

    public void revealFrom(Point point, View view, AnimatorListenerAdapter animatorListenerAdapter) {
        int targetRadius = ViewMeasurer.getRadiusFrom(point, view);
        int startRadius = 0;

        animateFor(view, point, startRadius, targetRadius, animatorListenerAdapter);
    }

    public void concealFrom(Point point, View view, AnimatorListenerAdapter animatorListenerAdapter) {
        int startRadius = ViewMeasurer.getRadiusFrom(point, view);
        int targetRadius = 0;

        animateFor(view, point, startRadius, targetRadius, animatorListenerAdapter);
    }

    private void animateFor(View view, Point origin, int startRadius, int targetRadius, final AnimatorListenerAdapter animatorListenerAdapter) {
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, origin.x, origin.y, startRadius, targetRadius);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
                animatorListenerAdapter.onAnimationStart(null);
            }

            @Override
            public void onAnimationEnd() {
                animatorListenerAdapter.onAnimationEnd(null);
            }

            @Override
            public void onAnimationCancel() {
                animatorListenerAdapter.onAnimationCancel(null);
            }

            @Override
            public void onAnimationRepeat() {
                animatorListenerAdapter.onAnimationRepeat(null);
            }
        });
        animator.setDuration(ANIMATION_DURATION);
        animator.start();
    }
}

package com.juankysoriano.materiallife.ui.sketch.reveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.view.View;

import com.juankysoriano.materiallife.ui.util.ViewMeasurer;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class ViewRevealer {

    private static final int ANIMATION_DURATION = 300;

    public void reveal(View view, AnimatorListenerAdapter animatorListenerAdapter) {
        Point origin = ViewMeasurer.getViewCenter(view);
        int targetRadius = ViewMeasurer.getRadiusFrom(origin, view);
        int startRadius = 0;

        animateFor(view, origin, startRadius, targetRadius, animatorListenerAdapter);
    }

    public void revealFrom(Point point, View view, AnimatorListenerAdapter animatorListenerAdapter) {
        int targetRadius = ViewMeasurer.getRadiusFrom(point, view);
        int startRadius = 0;

        animateFor(view, point, startRadius, targetRadius, animatorListenerAdapter);
    }

    public void conceal(View view, AnimatorListenerAdapter animatorListenerAdapter) {
        Point origin = ViewMeasurer.getViewCenter(view);
        int startRadius = ViewMeasurer.getRadiusFrom(origin, view);
        int targetRadius = 0;

        animateFor(view, origin, startRadius, targetRadius, animatorListenerAdapter);
    }

    public void concealFrom(Point point, View view, AnimatorListenerAdapter animatorListenerAdapter) {
        int startRadius = ViewMeasurer.getRadiusFrom(point, view);
        int targetRadius = 0;

        animateFor(view, point, startRadius, targetRadius, animatorListenerAdapter);
    }

    private void animateFor(View view, Point origin, int startRadius, int targetRadius, final AnimatorListenerAdapter animatorListenerAdapter) {
        if (isPreLollipop()) {
            doPreLollipopAnimation(view, origin, startRadius, targetRadius, animatorListenerAdapter);
        } else {
            doLollipopAnimation(view, origin, startRadius, targetRadius, animatorListenerAdapter);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void doLollipopAnimation(View view, Point origin, int startRadius, int targetRadius, AnimatorListenerAdapter animatorListenerAdapter) {
        Animator animator = android.view.ViewAnimationUtils.createCircularReveal(view, origin.x, origin.y, startRadius, targetRadius);
        animator.addListener(animatorListenerAdapter);
        animator.setDuration(ANIMATION_DURATION);
        animator.start();
    }

    private void doPreLollipopAnimation(View view, Point origin, int startRadius, int targetRadius, final AnimatorListenerAdapter animatorListenerAdapter) {
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

    private boolean isPreLollipop() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }
}

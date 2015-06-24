package com.juankysoriano.materiallife.ui.sketch.menu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class MenuButton extends FloatingActionButton {
    private static final String ALPHA = "alpha";
    private static final float OPAQUE = 1f;
    private static final float TRANSPARENT = 0f;
    private static final int HIDE_DURATION = 50;
    private static final int HIDE_DELAY = 0;
    private static final int SHOW_DURATION = 50;
    private static final int SHOW_DELAY = 400;
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();

    public MenuButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Animator animateShow() {
        Animator animator = buildAnimatorForProperty(ALPHA, TRANSPARENT, OPAQUE, SHOW_DURATION, SHOW_DELAY);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                setVisibility(View.VISIBLE);
            }
        });
        return animator;
    }

    public Animator animateHide() {
        Animator animator = buildAnimatorForProperty(ALPHA, OPAQUE, TRANSPARENT, HIDE_DURATION, HIDE_DELAY);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                setVisibility(View.INVISIBLE);
            }
        });
        return animator;
    }

    private Animator buildAnimatorForProperty(String property, float from, float to, int duration, int delay) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, property, from, to);
        animator.setDuration(duration);
        animator.setStartDelay(delay);
        animator.setInterpolator(ACCELERATE_INTERPOLATOR);
        return animator;
    }
}

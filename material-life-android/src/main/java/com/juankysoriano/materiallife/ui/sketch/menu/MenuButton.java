package com.juankysoriano.materiallife.ui.sketch.menu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class MenuButton extends FloatingActionButton {
    private static final String ALPHA = "alpha";
    private static final float OPAQUE = 1f;
    private static final float TRANSPARENT = 0f;
    private static final int HIDE_DURATION = 50;
    private static final int SHOW_DURATION = 50;
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();

    public MenuButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void animateShow() {
        Animator animator = buildAnimatorForProperty(ALPHA, TRANSPARENT, OPAQUE, SHOW_DURATION);
        animator.addListener(animationShowListener);
        animator.start();
    }

    public void animateHide() {
        Animator animator = buildAnimatorForProperty(ALPHA, OPAQUE, TRANSPARENT, HIDE_DURATION);
        animator.addListener(animationHideListener);
        animator.start();
    }

    private final AnimatorListenerAdapter animationHideListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            setVisibility(View.INVISIBLE);
        }
    };

    private final AnimatorListenerAdapter animationShowListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationStart(Animator animation) {
            setVisibility(View.VISIBLE);
        }
    };

    private Animator buildAnimatorForProperty(String property, float from, float to, int duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, property, from, to);
        animator.setDuration(duration);
        animator.setInterpolator(ACCELERATE_INTERPOLATOR);
        return animator;
    }
}

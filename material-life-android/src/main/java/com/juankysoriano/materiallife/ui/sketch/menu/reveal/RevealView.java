package com.juankysoriano.materiallife.ui.sketch.menu.reveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.juankysoriano.materiallife.ui.sketch.menu.NormalisedCenter;

public class RevealView extends View {
    private RevealDrawable revealDrawable;

    public RevealView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RevealView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        revealDrawable = RevealDrawable.newInstance(getContext());
        setBackground(revealDrawable);
    }

    public void reveal() {
        if (!isAnimationRunning()) {
            doReveal(null);
        }
    }

    public void reveal(AnimatorListenerAdapter animationListener) {
        if (!isAnimationRunning()) {
            doReveal(animationListener);
        }
    }

    private void doReveal(final AnimatorListenerAdapter animationListener) {
        revealDrawable.startReveal(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(animationListener != null) {
                    animationListener.onAnimationStart(animation);
                }
                show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (animationListener != null) {
                    animationListener.onAnimationEnd(animation);
                }
            }
        });
    }

    public void setCentreFrom(NormalisedCenter normalisedCenter) {
        revealDrawable.setCenterFrom(normalisedCenter);
    }

    public void conceal() {
        if (!isAnimationRunning()) {
            doConceal();
        }
    }

    private void doConceal() {
        revealDrawable.startHide(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                hide();
            }
        });
    }

    private boolean isAnimationRunning() {
        return revealDrawable.isRunning();
    }

    private void show() {
        setVisibility(VISIBLE);
    }

    private void hide() {
        setVisibility(INVISIBLE);
    }

    public void setRevealTargetRadius(int radius) {
        revealDrawable.setTargetRadius(radius);
    }

    public void setRevealFromRadius(int radius) {
        revealDrawable.setFromRadius(radius);
    }
}

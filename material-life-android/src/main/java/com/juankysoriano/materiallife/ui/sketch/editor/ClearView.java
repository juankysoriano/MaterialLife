package com.juankysoriano.materiallife.ui.sketch.editor;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.juankysoriano.materiallife.ui.sketch.reveal.ViewRevealer;

public class ClearView extends View {
    private static final float OPAQUE = 1f;
    private static final float TRANSPARENT = 0f;
    private static final int HIDE_DELAY = 100;
    private ViewRevealer viewRevealer;

    public ClearView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClearView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        viewRevealer = new ViewRevealer();
    }

    public void animateClearFrom(Point point, final EditorMenuView.ClearAnimationListener clearAnimationListener) {
        viewRevealer.revealFrom(point, this, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                clearAnimationListener.onClearDone();
                hide();
            }
        });
    }

    private void show() {
        setAlpha(OPAQUE);
        setVisibility(View.VISIBLE);
    }

    private void hide() {
        animate().alpha(TRANSPARENT)
                .setListener(hideAnimationListener)
                .setStartDelay(HIDE_DELAY)
                .start();
    }

    private AnimatorListenerAdapter hideAnimationListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            setVisibility(View.INVISIBLE);
        }
    };
}

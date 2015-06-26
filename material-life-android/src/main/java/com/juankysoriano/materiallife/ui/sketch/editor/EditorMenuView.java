package com.juankysoriano.materiallife.ui.sketch.editor;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.editor.EditorAction;
import com.juankysoriano.materiallife.ui.sketch.PixelButton;
import com.juankysoriano.materiallife.ui.sketch.menu.NormalisedCenter;
import com.juankysoriano.materiallife.ui.sketch.reveal.RevealView;
import com.juankysoriano.materiallife.ui.util.ViewMeasurer;
import com.novoda.notils.caster.Views;

public class EditorMenuView extends RelativeLayout {
    private static final NormalisedCenter REVEAL_CENTER = new NormalisedCenter(0.5f, 1f);
    private static final float OPAQUE = 1f;
    private static final float TRANSPARENT = 0f;
    private RevealView revealView;
    private PixelButton clearButton;

    public EditorMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditorMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        revealView = Views.findById(this, R.id.reveal_view);
        clearButton = Views.findById(this, R.id.edit_fab_clear);
    }

    public void animateClear(final ClearAnimationListener clearAnimationListener) {
        configureReveal();
        revealView.reveal(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                clearAnimationListener.onClearDone();
                revealView.animate().alpha(TRANSPARENT).start();
            }
        });
    }

    private void configureReveal() {
        revealView.setAlpha(OPAQUE);
        revealView.setRevealFromRadius(clearButton.getHeight() / 2);
        revealView.setRevealTargetRadius(ViewMeasurer.getRadiusFor(this) * 2);
        revealView.setCentreFrom(REVEAL_CENTER);
    }


    public void show() {
        setVisibility(View.VISIBLE);
    }

    public void hide() {
        setVisibility(View.GONE);
    }

    public interface ClearAnimationListener {
        void onClearDone();
    }

    public interface OnActionSelectedListener {
        void onActionPerformed(EditorAction action);
    }
}

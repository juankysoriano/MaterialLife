package com.juankysoriano.materiallife.ui.sketch.editor;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.ui.sketch.PixelButton;
import com.juankysoriano.materiallife.ui.sketch.menu.NormalisedCenter;
import com.juankysoriano.materiallife.ui.sketch.menu.reveal.RevealView;
import com.juankysoriano.materiallife.ui.util.ViewMeasurer;
import com.novoda.notils.caster.Views;

public class EditorView extends RelativeLayout {
    private static final NormalisedCenter REVEAL_CENTER = new NormalisedCenter(0.5f, 1f);
    private static final float OPAQUE = 1f;
    private static final float TRANSPARENT = 0f;
    private EditionListener editionListener;
    private RevealView revealView;
    private PixelButton acceptButton;
    private PixelButton cancelButton;
    private PixelButton clearButton;

    public EditorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        revealView = Views.findById(this, R.id.reveal_view);
        acceptButton = Views.findById(this, R.id.edit_fab_accept);
        cancelButton = Views.findById(this, R.id.edit_fab_cancel);
        clearButton = Views.findById(this, R.id.edit_fab_clear);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        acceptButton.setOnClickListener(onAcceptClickListener);
        clearButton.setOnClickListener(onClearClickListener);
        cancelButton.setOnClickListener(onCancelClickListener);
    }

    public void setEditionListener(EditionListener editionListener) {
        this.editionListener = editionListener;
    }

    private boolean hasEditionListener() {
        return editionListener != null;
    }

    private OnClickListener onAcceptClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (hasEditionListener()) {
                editionListener.onAccept();
            }
        }
    };

    private OnClickListener onClearClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            configureReveal();
            revealView.reveal(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (hasEditionListener()) {
                        editionListener.onClear();
                    }
                    revealView.animate().alpha(TRANSPARENT).start();
                }
            });
        }
    };

    private void configureReveal() {
        revealView.setAlpha(OPAQUE);
        revealView.setRevealFromRadius(clearButton.getHeight() / 2);
        revealView.setRevealTargetRadius(ViewMeasurer.getRadiusFor(this) * 2);
        revealView.setCentreFrom(REVEAL_CENTER);
    }

    private OnClickListener onCancelClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (hasEditionListener()) {
                editionListener.onCancel();
            }
        }
    };

    public void show() {
        setVisibility(View.VISIBLE);
    }

    public void hide() {
        setVisibility(View.GONE);
    }

    public interface EditionListener {
        void onAccept();

        void onClear();

        void onCancel();
    }
}

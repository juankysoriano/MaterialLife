package com.juankysoriano.materiallife.ui.sketch.menu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;

import com.juankysoriano.materiallife.menu.MenuItem;
import com.novoda.notils.caster.Classes;

public class MenuOptionsView extends ListView {
    private static final String ALPHA = "alpha";
    private static final String SCALE_X = "scaleX";
    private static final String SCALE_Y = "scaleY";
    private static final float FULL = 1f;
    private static final float NONE = 0f;
    private static final float OPAQUE = 1f;
    private static final float TRANSPARENT = 0f;
    private static final int SHOW_DURATION = 250;
    private static final int SHOW_DELAY = 50;
    private static final int HIDE_DURATION = 300;
    private static final int HIDE_DELAY = 0;
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();

    private OnItemSelectedListener onMenuOnItemSelectedListener;

    public MenuOptionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuOptionsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnItemClickListener(onItemSelectedListener);
    }

    public void setOnMenuItemSelectedListener(OnItemSelectedListener onMenuItemSelectedListener) {
        this.onMenuOnItemSelectedListener = onMenuItemSelectedListener;
    }

    private boolean hasOnMenuItemSelectedListener() {
        return onMenuOnItemSelectedListener != null;
    }

    private AdapterView.OnItemClickListener onItemSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setSelection(position);
            if (hasOnMenuItemSelectedListener()) {
                MenuItemAdapter adapter = Classes.from(parent.getAdapter());
                onMenuOnItemSelectedListener.onItemSelected(adapter.getItem(position));
            }
        }
    };

    @Override
    protected void onDetachedFromWindow() {
        setOnItemSelectedListener(null);
        super.onDetachedFromWindow();
    }

    /**
     * TODO this animators and all the others could be extracted to a builder class.
     * We can therefore remove duplicated code, guarantee SRP, and unit test this ;)
     *
     * @return
     */

    public Animator animateShow() {
        Animator alphaAnimator = buildAnimatorForProperty(ALPHA, TRANSPARENT, OPAQUE, SHOW_DURATION, SHOW_DELAY);
        Animator scaleXAnimator = buildAnimatorForProperty(SCALE_X, NONE, FULL, SHOW_DURATION, SHOW_DELAY);
        Animator scaleYAnimator = buildAnimatorForProperty(SCALE_Y, NONE, FULL, SHOW_DURATION, SHOW_DELAY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                show();
            }
        });
        return animatorSet;
    }

    public Animator animateHide() {
        Animator alphaAnimator = buildAnimatorForProperty(ALPHA, OPAQUE, TRANSPARENT, HIDE_DURATION, HIDE_DELAY);
        Animator scaleXAnimator = buildAnimatorForProperty(SCALE_X, FULL, NONE, HIDE_DURATION, HIDE_DELAY);
        Animator scaleYAnimator = buildAnimatorForProperty(SCALE_Y, FULL, NONE, HIDE_DURATION, HIDE_DELAY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                hide();
            }
        });
        return animatorSet;
    }

    private Animator buildAnimatorForProperty(String property, float from, float to, int duration, int delay) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, property, from, to);
        animator.setDuration(duration);
        animator.setStartDelay(delay);
        animator.setInterpolator(ACCELERATE_INTERPOLATOR);
        return animator;
    }

    private void show() {
        setVisibility(View.VISIBLE);
    }

    private void hide() {
        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setVisibility(View.GONE);
            }
        }, HIDE_DURATION);
    }

    public interface OnItemSelectedListener {
        void onItemSelected(MenuItem item);
    }
}

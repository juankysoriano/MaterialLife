package com.juankysoriano.materiallife.ui.sketch.menu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.ui.sketch.reveal.RevealView;
import com.juankysoriano.materiallife.ui.util.ViewMeasurer;
import com.novoda.notils.caster.Views;

public class MenuView extends RelativeLayout {
    private RevealView revealView;
    private MenuButton menuButton;
    private MenuOptionsView menuOptionsView;
    private boolean menuOpened;

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        revealView = Views.findById(this, R.id.reveal_view);
        menuButton = Views.findById(this, R.id.menu_fab_button);
        menuOptionsView = Views.findById(this, R.id.menu_list);
        menuOptionsView.setAdapter(MenuItemAdapter.newInstance());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        revealView.setRevealFromRadius(menuButton.getWidth() / 2);
        revealView.setRevealTargetRadius(ViewMeasurer.getRadiusFor(revealView) + menuButton.getWidth());
        revealView.setCentreFrom(ViewMeasurer.getViewNormalisedCenter(menuButton, this));
    }

    public void openMenu() {
        menuOpened = true;
        doRevealAnimation();
    }

    public void closeMenu() {
        menuOpened = false;
        doConcealAnimation();
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    public void hide() {
        setVisibility(View.GONE);
    }

    public boolean isMenuOpened() {
        return menuOpened;
    }

    private void doRevealAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(menuOptionsView.animateShow(), menuButton.animateHide());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                revealView.reveal();
            }
        });
        animatorSet.start();
    }

    private void doConcealAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(menuOptionsView.animateHide(), menuButton.animateShow());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                revealView.conceal();
            }
        });
        animatorSet.start();
    }
}

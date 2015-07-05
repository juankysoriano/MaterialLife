package com.juankysoriano.materiallife.ui.sketch.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.ui.util.ViewMeasurer;
import com.novoda.notils.caster.Views;

public class MenuView extends RelativeLayout {
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
        menuButton = Views.findById(this, R.id.menu_fab_button);
        menuOptionsView = Views.findById(this, R.id.menu_list);
        menuOptionsView.setAdapter(MenuItemAdapter.newInstance());
    }

    public void openMenu() {
        menuOpened = true;
        doRevealAnimation();
    }

    public void closeMenu() {
        menuOpened = false;
        doConcealAnimation();
    }

    public boolean isMenuOpened() {
        return menuOpened;
    }

    private void doRevealAnimation() {
        menuButton.animateHide();
        menuOptionsView.revealFrom(ViewMeasurer.getViewCenter(menuButton));
    }

    private void doConcealAnimation() {
        menuButton.animateShow();
        menuOptionsView.concealFrom(ViewMeasurer.getViewCenter(menuButton));
    }
}

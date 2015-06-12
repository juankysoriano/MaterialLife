package com.juankysoriano.materiallife.ui.menu.buttons.creators;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;

import com.juankysoriano.materiallife.ContextRetriever;
import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.ui.menu.buttons.FloatingActionButton;
import com.juankysoriano.materiallife.ui.menu.buttons.MenuButton;
import com.oguzdev.circularfloatingactionmenu.library.CircularMenu;

public final class CircularMenuCreator {
    private static final int MENU_RADIUS = ContextRetriever.INSTANCE.getResources().getDimensionPixelSize(R.dimen.menu_floating_action_button_radius);
    private static final int BUTTON_SIZE = ContextRetriever.INSTANCE.getResources().getDimensionPixelSize(R.dimen.menu_floating_action_button_size);
    private static final int MARGIN = ContextRetriever.INSTANCE.getResources().getDimensionPixelOffset(R.dimen.action_button_margin);
    private static final int MENU_COLOR = ContextRetriever.INSTANCE.getResources().getColor(R.color.colorAccent);
    private static final int START_ANGLE = 275;
    private static final int END_ANGLE = 175;

    private CircularMenuCreator() {
        //no-op
    }

    public static CircularMenu create() {
        Context context = ContextRetriever.INSTANCE.getApplicationContext();
        return new CircularMenu.Builder(context)
                .setRadius(MENU_RADIUS)
                .addSubActionView(SubActionButtonCreator.createFrom(context,
                        R.color.colorPrimary,
                        R.mipmap.ic_launcher,
                        MenuButton.PLAY.getId()))
                .addSubActionView(SubActionButtonCreator.createFrom(context,
                        R.color.colorPrimary,
                        R.mipmap.ic_launcher,
                        MenuButton.CLEAR.getId()))
                .addSubActionView(SubActionButtonCreator.createFrom(context,
                        R.color.colorPrimaryLight,
                        R.mipmap.ic_launcher,
                        MenuButton.RAMDOMISE.getId()))
                .addSubActionView(SubActionButtonCreator.createFrom(context,
                        R.color.colorPrimaryLight,
                        R.mipmap.ic_launcher,
                        MenuButton.SETTINGS.getId()))
                .attachTo(createMenuButtonWith(context,
                        R.mipmap.ic_launcher,
                        MenuButton.MENU.getId()))
                .setStartAngle(START_ANGLE)
                .setEndAngle(END_ANGLE)
                .build();
    }

    private static FloatingActionButton createMenuButtonWith(Context context, @DrawableRes int drawableId, @IdRes int resId) {
        return new FloatingActionButton.Builder(context)
                .withButtonSize(BUTTON_SIZE)
                .withMargins(MARGIN, MARGIN, MARGIN, MARGIN)
                .withButtonColor(MENU_COLOR)
                .withGravity(Gravity.BOTTOM | Gravity.END)
                .withDrawable(ContextCompat.getDrawable(ContextRetriever.INSTANCE.getApplicationContext(), drawableId))
                .withId(resId)
                .createInto(getActivityContentView());
    }

    private static ViewGroup getActivityContentView() {
        return (ViewGroup) ContextRetriever.INSTANCE.getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
    }

}

package com.juankysoriano.materiallife.menu;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.ui.sketch.menu.MenuButton;
import com.juankysoriano.materiallife.ui.sketch.menu.MenuOptionsView;
import com.juankysoriano.materiallife.ui.sketch.menu.MenuView;
import com.novoda.notils.caster.Views;

public class MainMenuFragment extends Fragment implements MainMenu {
    public static final String TAG = "MainMenuFragment";

    private MenuView menuView;
    private MenuOptionsView menuOptionsView;
    private MenuButton menuButton;
    private MenuOptionsView.OnItemSelectedListener attachedItemSelectedListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.include_menu_view, container, false);
        menuView = Views.findById(fragmentView, R.id.menu_layout);
        menuOptionsView = Views.findById(fragmentView, R.id.menu_list);
        menuButton = Views.findById(fragmentView, R.id.menu_fab_button);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachListeners();
    }

    private void attachListeners() {
        menuButton.setOnClickListener(onMenuButtonClickListener);
        menuOptionsView.setOnMenuItemSelectedListener(onItemSelectedListener);
    }

    private final View.OnClickListener onMenuButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menuView.openMenu();
        }
    };

    private final MenuOptionsView.OnItemSelectedListener onItemSelectedListener = new MenuOptionsView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(MenuItem item) {
            if (hasAttachedItemSelectedListener()) {
                attachedItemSelectedListener.onItemSelected(item);
            }
        }
    };

    @Override
    public void attachItemSelectedListener(MenuOptionsView.OnItemSelectedListener listener) {
        attachedItemSelectedListener = listener;
    }

    @Override
    public void detachItemSelectedListener() {
        attachedItemSelectedListener = null;
    }

    @Override
    public boolean isOpened() {
        return menuView.isMenuOpened();
    }

    @Override
    public void closeMenu() {
        menuView.closeMenu();
    }

    @Override
    public void openMenu() {
        menuView.openMenu();
    }

    @Override
    public MainMenuFragment getFragment() {
        return this;
    }

    private boolean hasAttachedItemSelectedListener() {
        return attachedItemSelectedListener != null;
    }

    private void detachListeners() {
        menuButton.setOnClickListener(null);
        menuOptionsView.setOnMenuItemSelectedListener(null);
    }

    @Override
    public void onDestroyView() {
        detachListeners();
        super.onDestroyView();
    }
}

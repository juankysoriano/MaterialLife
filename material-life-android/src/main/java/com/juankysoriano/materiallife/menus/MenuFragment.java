package com.juankysoriano.materiallife.menus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.ui.sketch.menu.MenuButton;
import com.juankysoriano.materiallife.ui.sketch.menu.MenuItem;
import com.juankysoriano.materiallife.ui.sketch.menu.MenuOptions;
import com.juankysoriano.materiallife.ui.sketch.menu.MenuView;
import com.novoda.notils.caster.Views;

public class MenuFragment extends Fragment {

    private MenuView menuView;
    private MenuOptions menuOptions;
    private MenuButton menuButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.include_menu_view, container, false);
        menuView = Views.findById(fragmentView, R.id.menu_layout);
        menuOptions = Views.findById(fragmentView, R.id.menu_list);
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
        menuOptions.setOnMenuItemSelectedListener(onItemSelectedListener);
    }

    private View.OnClickListener onMenuButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menuView.openMenu();
        }
    };

    private MenuOptions.OnItemSelectedListener onItemSelectedListener = new MenuOptions.OnItemSelectedListener() {
        @Override
        public void onItemSelected(MenuItem item) {
            switch (item) {
                case EDIT_WORLD:
                    /*worldEditor.startEdition();*/
                    break;
            }
        }
    };

    private void detachListeners() {
        menuButton.setOnClickListener(null);
        menuOptions.setOnMenuItemSelectedListener(null);
    }

    @Override
    public void onDestroyView() {
        detachListeners();
        super.onDestroyView();
    }
}

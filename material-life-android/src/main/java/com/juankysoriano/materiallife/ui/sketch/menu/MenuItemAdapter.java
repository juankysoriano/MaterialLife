package com.juankysoriano.materiallife.ui.sketch.menu;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.juankysoriano.materiallife.ContextRetriever;
import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.menu.MenuItem;
import com.juankysoriano.materiallife.ui.sketch.PixelTextView;
import com.novoda.notils.caster.Views;

public class MenuItemAdapter extends BaseAdapter {

    private final Context context;

    public static MenuItemAdapter newInstance() {
        return new MenuItemAdapter(ContextRetriever.INSTANCE.getApplicationContext());
    }

    @VisibleForTesting
    protected MenuItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return MenuItem.values().length;
    }

    @Override
    public MenuItem getItem(int position) {
        return MenuItem.values()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        PixelTextView menuItemTextView = Views.findById(view, R.id.menu_item);
        MenuItem menuItem = getItem(position);
        menuItemTextView.setText(menuItem.value());

        return view;
    }
}

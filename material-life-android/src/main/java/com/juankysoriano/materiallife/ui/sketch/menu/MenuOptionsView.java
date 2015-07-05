package com.juankysoriano.materiallife.ui.sketch.menu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.juankysoriano.materiallife.menu.MenuItem;
import com.juankysoriano.materiallife.ui.sketch.reveal.ViewRevealer;
import com.novoda.notils.caster.Classes;

public class MenuOptionsView extends ListView {

    private OnItemSelectedListener onMenuOnItemSelectedListener;
    private ViewRevealer viewRevealer;

    public MenuOptionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuOptionsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        viewRevealer = new ViewRevealer();
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

    private final AdapterView.OnItemClickListener onItemSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setSelection(position);
            if (hasOnMenuItemSelectedListener()) {
                MenuItemAdapter adapter = Classes.from(parent.getAdapter());
                onMenuOnItemSelectedListener.onItemSelected(adapter.getItem(position));
            }
        }
    };

    public void revealFrom(Point point) {
        viewRevealer.revealFrom(point, this, revealAnimationListener);
    }

    public void concealFrom(Point point) {
        viewRevealer.concealFrom(point, this, concealAnimationListener);
    }

    private final AnimatorListenerAdapter revealAnimationListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationStart(Animator animation) {
            show();
        }
    };

    private final AnimatorListenerAdapter concealAnimationListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            hide();
        }
    };

    private void show() {
        setVisibility(View.VISIBLE);
    }

    private void hide() {
        setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDetachedFromWindow() {
        setOnItemSelectedListener(null);
        super.onDetachedFromWindow();
    }

    public interface OnItemSelectedListener {
        void onItemSelected(MenuItem item);
    }
}

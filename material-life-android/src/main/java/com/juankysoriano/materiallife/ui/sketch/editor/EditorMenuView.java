package com.juankysoriano.materiallife.ui.sketch.editor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.editor.EditorAction;
import com.juankysoriano.materiallife.ui.sketch.PixelButton;
import com.juankysoriano.materiallife.ui.util.ViewMeasurer;
import com.novoda.notils.caster.Views;

public class EditorMenuView extends RelativeLayout {
    private ClearView clearView;
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
        clearView = Views.findById(this, R.id.clear_view);
        clearButton = Views.findById(this, R.id.edit_fab_clear);
    }

    public void animateClear(final ClearAnimationListener clearAnimationListener) {
        clearView.animateClearFrom(ViewMeasurer.getViewCenterOnScreen(clearButton), clearAnimationListener);
    }

    public interface ClearAnimationListener {
        void onClearDone();
    }

    public interface OnActionSelectedListener {
        void onActionPerformed(EditorAction action);
    }
}

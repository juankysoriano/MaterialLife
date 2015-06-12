package com.juankysoriano.materiallife.ui.sketch;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.ui.menu.buttons.FloatingActionButton;
import com.juankysoriano.materiallife.ui.menu.buttons.MenuButton;
import com.juankysoriano.materiallife.ui.menu.buttons.creators.CircularMenuCreator;
import com.juankysoriano.materiallife.ui.sketch.clear.ClearView;
import com.juankysoriano.materiallife.ui.sketch.clear.WorldClearListener;
import com.juankysoriano.rainbow.utils.RainbowMath;
import com.oguzdev.circularfloatingactionmenu.library.CircularMenu;


@SuppressWarnings({"PMD.FieldDeclarationsShouldBeAtStartOfClass", "PMD.TooManyMethods"})
public class WorldView extends RelativeLayout {
    private ClearView clearView;
    private WorldClearListener worldClearListener;
    private CircularMenu circularMenu;

    public WorldView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WorldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        clearView = (ClearView) findViewById(R.id.reveal_view);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        circularMenu = CircularMenuCreator.create();

    }

    public CircularMenu getCircularMenu() {
        return circularMenu;
    }

    public void setWorldClearListener(WorldClearListener onClearListener) {
        this.worldClearListener = onClearListener;
    }

    private boolean hasSketchClearListener() {
        return worldClearListener != null;
    }

    private final Animator.AnimatorListener revealAnimatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            if (hasSketchClearListener()) {
                worldClearListener.onWorldCleared();
            }
        }
    };

    public void clear() {
        if (!clearView.isClearing()) {
            clearView.startClearWith(revealAnimatorListener);
            FloatingActionButton menu = (FloatingActionButton) (circularMenu.findSubActionViewWithId(MenuButton.CLEAR.getId()));
            clearView.setClearOrigin(menu.getCentre());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        clearView.setClearRadius(RainbowMath.dist(0, 0, getWidth(), getHeight()));
    }
}

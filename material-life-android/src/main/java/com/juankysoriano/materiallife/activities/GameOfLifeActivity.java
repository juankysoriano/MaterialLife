package com.juankysoriano.materiallife.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.menus.MenuFragment;
import com.juankysoriano.materiallife.world.World;
import com.juankysoriano.materiallife.world.actions.WorldEditor;
import com.novoda.notils.caster.Views;

public class GameOfLifeActivity extends MaterialLifeActivity implements View.OnAttachStateChangeListener {

    private final World world;
    private WorldEditor worldEditor;
    private FrameLayout worldView;
    private MenuFragment menuFragment;

    public GameOfLifeActivity() {
        this(World.newInstance());
    }

    public GameOfLifeActivity(World world) {
        this.world = world;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world);

        worldView = Views.findById(this, R.id.world);
        //worldEditor = WorldEditor.newInstance(world);
        worldView.addOnAttachStateChangeListener(this);
        menuFragment = new MenuFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        world.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        world.resume();
    }

    @Override
    public void onViewAttachedToWindow(View v) {
        world.injectInto(worldView);
    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        //no-op
    }

    @Override
    protected void onPause() {
        world.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        world.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        world.destroy();
        super.onDestroy();
    }
}

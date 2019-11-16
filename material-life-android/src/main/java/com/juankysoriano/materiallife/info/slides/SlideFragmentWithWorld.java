package com.juankysoriano.materiallife.info.slides;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.juankysoriano.materiallife.world.World;
import com.novoda.notils.caster.Views;

public abstract class SlideFragmentWithWorld extends Fragment {
    private World world;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getSlideLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout worldView = Views.findById(view, getWorldViewId());
        world = instantiateWorld();
        world.injectInto(worldView);
    }

    @Override
    public void onStart() {
        super.onStart();
        world.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        world.resume();
    }

    @Override
    public void onPause() {
        world.pause();
        super.onPause();
    }

    @Override
    public void onStop() {
        world.stop();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        world.destroy();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        world.destroy();
        super.onDestroy();
    }

    protected abstract World instantiateWorld();

    protected abstract int getSlideLayoutId();

    protected abstract int getWorldViewId();
}

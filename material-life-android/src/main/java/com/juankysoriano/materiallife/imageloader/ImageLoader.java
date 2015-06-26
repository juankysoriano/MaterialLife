package com.juankysoriano.materiallife.imageloader;

public interface ImageLoader {
    void attachLoadImageListener(OnLoadImageSelectedListener listener);

    void detachActionSelectedListener();

    ImageLoaderFragment getFragment();

    interface OnLoadImageSelectedListener {
        void onLoadImage(ImageLoaderAction action);
    }
}

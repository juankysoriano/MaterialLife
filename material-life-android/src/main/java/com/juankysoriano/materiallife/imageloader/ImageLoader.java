package com.juankysoriano.materiallife.imageloader;

import android.net.Uri;

public interface ImageLoader {
    void attachLoadImageListener(OnLoadImageSelectedListener listener);

    void detachActionSelectedListener();

    ImageLoaderFragment getFragment();

    void loadWorldFrom(Uri imageUri);

    void abortPictureLoading();

    interface OnLoadImageSelectedListener {
        void onLoadImage(ImageLoaderAction action);
    }
}

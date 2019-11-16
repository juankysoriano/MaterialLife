package com.juankysoriano.materiallife.imageloader;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.editor.WorldEditor;
import com.juankysoriano.materiallife.ui.sketch.PixelButton;
import com.novoda.notils.caster.Views;

public class ImageLoaderFragment extends Fragment implements ImageLoader {
    public static final String TAG = "ImageLoaderFragment";

    private PixelButton cameraButton;
    private PixelButton galleryButton;
    private OnLoadImageSelectedListener onLoadImageSelectedListener;
    private WorldEditor worldEditor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.include_image_selector, container, false);
        cameraButton = Views.findById(fragmentView, R.id.from_camera);
        galleryButton = Views.findById(fragmentView, R.id.from_gallery);
        worldEditor = WorldEditor.newInstance();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachListeners();
    }

    private void attachListeners() {
        cameraButton.setOnClickListener(onCameraClickListener);
        galleryButton.setOnClickListener(onGalleryClickListener);
    }

    private final PixelButton.OnClickListener onCameraClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (hasLoadImageListener()) {
                worldEditor.startEdition();
                onLoadImageSelectedListener.onLoadImage(ImageLoaderAction.CAMERA);
            }
        }
    };

    private final PixelButton.OnClickListener onGalleryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (hasLoadImageListener()) {
                worldEditor.startEdition();
                onLoadImageSelectedListener.onLoadImage(ImageLoaderAction.GALLERY);
            }
        }
    };

    @Override
    public void attachLoadImageListener(OnLoadImageSelectedListener listener) {
        onLoadImageSelectedListener = listener;
    }

    @Override
    public void detachActionSelectedListener() {
        onLoadImageSelectedListener = null;
    }

    private boolean hasLoadImageListener() {
        return onLoadImageSelectedListener != null;
    }

    @Override
    public ImageLoaderFragment getFragment() {
        return this;
    }

    @Override
    public void loadWorldFrom(Uri imageUri) {
        worldEditor.loadWorldFrom(imageUri);
    }

    @Override
    public void abortPictureLoading() {
        worldEditor.cancel();
        worldEditor.endEdition();
    }

    private void detachListeners() {
        cameraButton.setOnClickListener(null);
        galleryButton.setOnClickListener(null);
    }

    @Override
    public void onDestroyView() {
        detachListeners();
        super.onDestroyView();
    }
}

package com.juankysoriano.materiallife.navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.juankysoriano.materiallife.ContextRetriever;
import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.imageloader.ImageLoaderResult;
import com.novoda.notils.logger.simple.Log;

import java.io.File;
import java.io.IOException;

import androidx.core.content.FileProvider;

public class PictureRetriever {
    private static final String MATERIAL_LIFE = "MATERIAL_LIFE";
    private static final String EXTENSION = ".jpg";
    private static final String FILE_SCHEME = "file:";

    private String lastLoadedImagePath;

    public void openCameraForResult() {
        Intent intent = createIntentForCamera();
        if (intent != null) {
            ContextRetriever.INSTANCE.getActivity().startActivityForResult(intent, ImageLoaderResult.CAMERA.getCode());
        }
    }

    public Uri getLastLoadedImagePath() {
        return Uri.parse(lastLoadedImagePath);
    }

    public void openGalleryForResult() {
        ContextRetriever.INSTANCE.getActivity().startActivityForResult(createIntentForGallery(), ImageLoaderResult.GALLERY.getCode());
    }

    private Intent createIntentForCamera() {
        try {
            return new Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    .putExtra(MediaStore.EXTRA_OUTPUT, createImageFile());
        } catch (IOException e) {
            Log.e("Problem creating picture:" + e);
        }
        return null;
    }

    private Uri createImageFile() throws IOException {
        File storageDir = ContextRetriever.INSTANCE.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(MATERIAL_LIFE, EXTENSION, storageDir);
        lastLoadedImagePath = FILE_SCHEME + image.getAbsolutePath();
        return FileProvider.getUriForFile(
                ContextRetriever.INSTANCE.getApplicationContext(),
                ContextRetriever.INSTANCE.getResources().getString(R.string.file_provider),
                image
        );
    }

    private Intent createIntentForGallery() {
        return new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }
}

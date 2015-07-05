package com.juankysoriano.materiallife.navigation;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;

import com.juankysoriano.materiallife.ContextRetriever;
import com.juankysoriano.materiallife.MaterialLifeTestBase;
import com.juankysoriano.materiallife.RobolectricMaterialLifeGradleTestRunner;
import com.juankysoriano.materiallife.imageloader.ImageLoaderResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricMaterialLifeGradleTestRunner.class)
public class PictureRetrieverTest extends MaterialLifeTestBase {
    @Mock
    private FragmentActivity mockActivity;
    private ArgumentCaptor<Intent> intentCaptor;
    private PictureRetriever pictureRetriever;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ContextRetriever.INSTANCE.inject(mockActivity);

        intentCaptor = ArgumentCaptor.forClass(Intent.class);
        pictureRetriever = new PictureRetriever();
    }

    @Test
    public void testThatWhenOpenGalleryForResultActivityIsStartedForResult() {
        pictureRetriever.openGalleryForResult();

        verify(mockActivity).startActivityForResult(intentCaptor.capture(), eq(ImageLoaderResult.GALLERY.getCode()));
        assertThat(intentCaptor.getValue().filterEquals(expectedGalleryIntent()));
    }

    @Test
    public void testThatWhenOpenCameraForResultActivityIsStartedForResult() {
        pictureRetriever.openCameraForResult();

        verify(mockActivity).startActivityForResult(intentCaptor.capture(), eq(ImageLoaderResult.CAMERA.getCode()));
        assertThat(intentCaptor.getValue().filterEquals(expectedCameraIntent()));
    }

    private Intent expectedGalleryIntent() {
        return new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    private Intent expectedCameraIntent() {
        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }
}

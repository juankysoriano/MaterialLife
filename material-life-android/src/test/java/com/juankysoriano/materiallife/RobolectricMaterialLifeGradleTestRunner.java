package com.juankysoriano.materiallife;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;

public class RobolectricMaterialLifeGradleTestRunner extends RobolectricTestRunner {
    private static final int MAX_SDK_SUPPORTED_BY_ROBOLECTRIC = 21;

    public RobolectricMaterialLifeGradleTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        String manifestProperty = System.getProperty("android.manifest");
        if (config.manifest().equals(Config.DEFAULT) && manifestProperty != null) {
            String resProperty = System.getProperty("android.resources");
            String assetsProperty = System.getProperty("android.assets");
            AndroidManifest androidManifest = new AndroidManifest(
                    Fs.fileFromPath(manifestProperty),
                    Fs.fileFromPath(resProperty),
                    Fs.fileFromPath(assetsProperty)) {
                @Override
                public int getTargetSdkVersion() {
                    return MAX_SDK_SUPPORTED_BY_ROBOLECTRIC;
                }
            };
            androidManifest.setPackageName("com.juankysoriano.materiallife");
            return androidManifest;
        }
        return super.getAppManifest(config);
    }
}

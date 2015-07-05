package com.juankysoriano.materiallife;

import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;

@RunWith(RobolectricMaterialLifeGradleTestRunner.class)
public class MaterialLifeTestBase {
    public MaterialLifeTestBase() {
        ContextRetriever.INSTANCE.inject(RuntimeEnvironment.application);
    }
}

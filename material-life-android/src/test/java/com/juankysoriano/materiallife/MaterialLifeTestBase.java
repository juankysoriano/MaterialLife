package com.juankysoriano.materiallife;

import android.content.res.Resources;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MaterialLifeTestBase {
    protected static final int DEAD_COLOR = Integer.parseInt("030803", 16);
    protected static final int ALIVE_COLOR = Integer.parseInt("1B5E20", 16);
    protected static final int SCALE_FACTOR = 10;

    public MaterialLifeTestBase() {
        Resources resources = mock(Resources.class);
        when(resources.getColor(R.color.alive)).thenReturn(ALIVE_COLOR);
        when(resources.getColor(R.color.dead)).thenReturn(DEAD_COLOR);
        when(resources.getInteger(R.integer.cell_size)).thenReturn(SCALE_FACTOR);
        MaterialLifeApplication application = mock(MaterialLifeApplication.class);
        when(application.getResources()).thenReturn(resources);
        ContextRetriever.INSTANCE.inject(application);
    }
}

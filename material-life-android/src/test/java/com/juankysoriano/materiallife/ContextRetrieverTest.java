package com.juankysoriano.materiallife;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ContextRetrieverTest extends MaterialLifeTestBase {

    @Mock
    private Application application;
    @Mock
    private Context applicationContext;
    @Mock
    private FragmentActivity activity;
    @Mock
    private Context activityContext;
    @Mock
    private Resources androidResources;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(application.getApplicationContext()).thenReturn(applicationContext);
        when(application.getResources()).thenReturn(androidResources);
    }

    @Test
    public void testThatWhenAnApplicationIsInjectedThenGetApplicationContextReturnsApplicationContext() {
        ContextRetriever.INSTANCE.inject(application);

        assertThat(ContextRetriever.INSTANCE.getApplicationContext()).isEqualTo(applicationContext);
    }

    @Test
    public void testThatWhenAnApplicationIsInjectedThenGetResourcesReturnsApplicationResources() {
        ContextRetriever.INSTANCE.inject(application);

        assertThat(ContextRetriever.INSTANCE.getResources()).isEqualTo(androidResources);
    }

    @Test
    public void testThatWhenAnActivityIsInjectedThenGetActivityReturnsInjectedActivity() {
        ContextRetriever.INSTANCE.inject(activity);

        assertThat(ContextRetriever.INSTANCE.getActivity()).isEqualTo(activity);
    }
}

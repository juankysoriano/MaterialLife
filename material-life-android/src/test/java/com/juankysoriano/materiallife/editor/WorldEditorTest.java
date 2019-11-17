package com.juankysoriano.materiallife.editor;

import android.net.Uri;

import com.juankysoriano.materiallife.MaterialLifeTestBase;
import com.juankysoriano.materiallife.world.World;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WorldEditorTest extends MaterialLifeTestBase {
    private static Uri ANY_URI = mock(Uri.class);
    @Mock
    private World world;
    private WorldEditor worldEditor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        worldEditor = new WorldEditor(world);
    }

    @Test
    public void testThatStartEditionDelegates() {
        worldEditor.startEdition();

        verify(world).startEdition();
    }

    @Test
    public void testThatEndEditionDelegates() {
        worldEditor.endEdition();

        verify(world).endEdition();
    }

    @Test
    public void testThatClearDelegates() {
        worldEditor.clear();

        verify(world).clear();
    }

    @Test
    public void testThatCancelRestoresLastWorld() {
        worldEditor.cancel();

        verify(world).restoreLastWorld();
    }

    @Test
    public void testThatCancelEndsEdition() {
        worldEditor.cancel();

        verify(world).endEdition();
    }

    @Test
    public void testThatLoadWorldFromDelegates() {
        worldEditor.loadWorldFrom(ANY_URI);

        verify(world).loadWorldFrom(ANY_URI);
    }
}

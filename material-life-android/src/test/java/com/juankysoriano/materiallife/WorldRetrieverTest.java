package com.juankysoriano.materiallife;

import com.juankysoriano.materiallife.world.World;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.assertThat;

public class WorldRetrieverTest extends MaterialLifeTestBase {
    @Mock
    private World world;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWhenAWorldIsInjectedThenGetWorldReturnsThatWorld() {
        WorldRetriever.INSTANCE.inject(world);

        assertThat(WorldRetriever.INSTANCE.getWorld()).isEqualTo(world);
    }
}

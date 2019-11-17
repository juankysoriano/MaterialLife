package com.juankysoriano.materiallife.ui.sketch.menu;

import com.juankysoriano.materiallife.ContextRetriever;
import com.juankysoriano.materiallife.MaterialLifeTestBase;
import com.juankysoriano.materiallife.menu.MenuItem;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class MenuItemAdapterTest extends MaterialLifeTestBase {

    @Parameterized.Parameters(name = "{0}->{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, MenuItem.EDIT_WORLD.value()},
                {1, MenuItem.LOAD_WORLD.value()},
                {2, MenuItem.INFO.value()}
        });
    }

    private final int position;
    private final String item;
    private MenuItemAdapter menuItemAdapter;

    @Before
    public void setUp() {
        menuItemAdapter = new MenuItemAdapter(ContextRetriever.INSTANCE.getApplicationContext());
    }

    public MenuItemAdapterTest(int position, String item) {
        this.position = position;
        this.item = item;
    }

    @Test
    public void testThatGetCountIsExactlyTheNumberOfMenuItems() {
        assertThat(menuItemAdapter.getCount()).isEqualTo(MenuItem.values().length);
    }

    @Test
    public void testThatGetItemReturnsFromMenuItem() {
        assertThat(menuItemAdapter.getItem(position).value()).isEqualTo(item);
    }

    @Test
    public void testThatGetItemIdReturnsItemPosition() {
        assertThat(menuItemAdapter.getItemId(position)).isEqualTo(position);
    }
}

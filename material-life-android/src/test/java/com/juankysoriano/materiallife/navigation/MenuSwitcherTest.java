package com.juankysoriano.materiallife.navigation;

import android.annotation.SuppressLint;

import com.juankysoriano.materiallife.MaterialLifeTestBase;
import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.editor.WorldEditorMenuFragment;
import com.juankysoriano.materiallife.imageloader.ImageLoaderFragment;
import com.juankysoriano.materiallife.menu.MainMenuFragment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("CommitTransaction")

public class MenuSwitcherTest extends MaterialLifeTestBase {
    @Mock
    private FragmentManager fragmentManager;
    @Mock
    private FragmentTransaction fragmentTransaction;
    private MenuSwitcher menuSwitcher;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(fragmentManager.beginTransaction()).thenReturn(fragmentTransaction);
        when(fragmentTransaction.replace(anyInt(), any(Fragment.class), anyString())).thenReturn(fragmentTransaction);

        menuSwitcher = new MenuSwitcher(fragmentManager);
    }

    @Test
    public void testThatWhenMainMenuIsNotPresentThenAddMainMenuBeginsTransaction() {
        givenThatMainMenuIsNotPresent();

        menuSwitcher.addMainMenu();

        verify(fragmentManager).beginTransaction();
    }

    @Test
    public void testThatWhenMainMenuIsNotPresentThenAddMainMenuPerformsReplaceAndCommit() {
        givenThatMainMenuIsNotPresent();

        menuSwitcher.addMainMenu();

        InOrder inOrder = inOrder(fragmentTransaction, fragmentTransaction);

        inOrder.verify(fragmentTransaction).replace(eq(R.id.menu_container), any(MainMenuFragment.class), eq(MainMenuFragment.TAG));
        inOrder.verify(fragmentTransaction).commit();
    }

    @Test
    public void testThatWhenMainMenuIsPresentThenAddMainMenuFindsFragmentByTag() {
        givenThatMainMenuIsPresent();

        menuSwitcher.addMainMenu();

        verify(fragmentManager, times(2)).findFragmentByTag(MainMenuFragment.TAG);
    }

    @Test
    public void testThatWhenEditorMenuIsNotPresentThenAddEditorMenuBeginsTransaction() {
        givenThatEditorMenuIsNotPresent();

        menuSwitcher.addEditorMenu();

        verify(fragmentManager).beginTransaction();
    }

    @Test
    public void testThatWhenEditorMenuIsNotPresentThenAddEditorMenuPerformsReplaceAndCommit() {
        givenThatEditorMenuIsNotPresent();

        menuSwitcher.addEditorMenu();

        InOrder inOrder = inOrder(fragmentTransaction, fragmentTransaction);

        inOrder.verify(fragmentTransaction).replace(eq(R.id.menu_container), any(WorldEditorMenuFragment.class), eq(WorldEditorMenuFragment.TAG));
        inOrder.verify(fragmentTransaction).commit();
    }

    @Test
    public void testThatWhenEditorMenuIsPresentThenAddEditorMenuFindsFragmentByTag() {
        givenThatEditorMenuIsPresent();

        menuSwitcher.addEditorMenu();

        verify(fragmentManager, times(2)).findFragmentByTag(WorldEditorMenuFragment.TAG);
    }

    @SuppressLint("CommitTransaction")
    @Test
    public void testThatWhenImageLoaderIsNotPresentThenAddImageLoaderBeginsTransaction() {
        givenThatImageLoaderIsNotPresent();

        menuSwitcher.addImageLoader();

        verify(fragmentManager).beginTransaction();
    }

    @Test
    public void testThatWhenImageLoaderIsNotPresentThenAddImageLoaderPerformsReplaceAndCommit() {
        givenThatImageLoaderIsNotPresent();

        menuSwitcher.addImageLoader();

        InOrder inOrder = inOrder(fragmentTransaction, fragmentTransaction);

        inOrder.verify(fragmentTransaction).replace(eq(R.id.menu_container), any(ImageLoaderFragment.class), eq(ImageLoaderFragment.TAG));
        inOrder.verify(fragmentTransaction).commit();
    }

    @Test
    public void testThatWhenImageLoaderIsPresentThenAddImageLoaderFindsFragmentByTag() {
        givenThatImageLoaderIsPresent();

        menuSwitcher.addImageLoader();

        verify(fragmentManager, times(2)).findFragmentByTag(ImageLoaderFragment.TAG);
    }

    private void givenThatMainMenuIsNotPresent() {
        when(fragmentManager.findFragmentByTag(MainMenuFragment.TAG)).thenReturn(null);
    }

    private void givenThatMainMenuIsPresent() {
        when(fragmentManager.findFragmentByTag(MainMenuFragment.TAG)).thenReturn(mock(MainMenuFragment.class));
    }

    private void givenThatEditorMenuIsNotPresent() {
        when(fragmentManager.findFragmentByTag(WorldEditorMenuFragment.TAG)).thenReturn(null);
    }

    private void givenThatEditorMenuIsPresent() {
        when(fragmentManager.findFragmentByTag(WorldEditorMenuFragment.TAG)).thenReturn(mock(WorldEditorMenuFragment.class));
    }

    private void givenThatImageLoaderIsNotPresent() {
        when(fragmentManager.findFragmentByTag(ImageLoaderFragment.TAG)).thenReturn(null);
    }

    private void givenThatImageLoaderIsPresent() {
        when(fragmentManager.findFragmentByTag(ImageLoaderFragment.TAG)).thenReturn(mock(ImageLoaderFragment.class));
    }

}

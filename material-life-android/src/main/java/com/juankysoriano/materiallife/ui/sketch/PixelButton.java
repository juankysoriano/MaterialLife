package com.juankysoriano.materiallife.ui.sketch;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class PixelButton extends Button {

    private static final String FONT_PATH = "fonts/pixel.ttf";

    public PixelButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFonts();
    }

    public PixelButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFonts();
    }

    private void initFonts() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), FONT_PATH);
        setTypeface(typeface);
    }
}
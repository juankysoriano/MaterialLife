package com.juankysoriano.materiallife.info.preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.juankysoriano.materiallife.ContextRetriever;

public class InfoPreferences {
    private static final String INFO_PREFERENCES = "InfoPreferences";
    private static final String ALREADY_SEEN = INFO_PREFERENCES + ".AlreadySeen";
    public static final String FROM_MENU = INFO_PREFERENCES + ".FromMenu";
    private final SharedPreferences sharedPreferences;

    protected InfoPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static InfoPreferences newInstance() {
        Context context = ContextRetriever.INSTANCE.getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(INFO_PREFERENCES,
                Context.MODE_PRIVATE);

        return new InfoPreferences(sharedPreferences);
    }

    public boolean shouldShowInfo(Intent launchIntent) {
        return !sharedPreferences.getBoolean(ALREADY_SEEN, false) || isFromMenu(launchIntent);
    }

    private boolean isFromMenu(Intent launchIntent) {
        return launchIntent.getBooleanExtra(FROM_MENU, false);
    }

    public void markInfoAsSeen() {
        sharedPreferences.edit().putBoolean(ALREADY_SEEN, true).apply();
    }
}

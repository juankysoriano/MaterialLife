package com.juankysoriano.materiallife;

public interface OnBackPressedListener {
    /**
     * To be executed wether the back button is hitten
     * @return true if the event was consumed, false otherwise.
     */
    boolean onBackPressed();
}

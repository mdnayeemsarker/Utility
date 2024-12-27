package com.abmn.utility.Core;

import android.graphics.Color;

public class Config {
    private static int startColor = Color.parseColor("#A867F6");
    private static int centerColor = Color.parseColor("#B593DF");
    private static int endColor = Color.parseColor("#6D29BF");

    public static int getStartColor() {
        return startColor;
    }

    public static int getCenterColor() {
        return centerColor;
    }

    public static int getEndColor() {
        return endColor;
    }

    // Method to set progress bar colors
    public static synchronized void setProgressColors(int start, int center, int end) {
        startColor = start;
        centerColor = center;
        endColor = end;
    }
}

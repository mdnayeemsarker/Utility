package com.abmn.utility.Core;

import android.graphics.Color;

public class Config {
    private static boolean dMode = false;
    private static String P_NAME = "abmn_utility";
    private static int startColor = Color.parseColor("#A867F6");
    private static int centerColor = Color.parseColor("#B593DF");
    private static int endColor = Color.parseColor("#6D29BF");
    public static synchronized void init(String PREFER_NAME, boolean debugMode) {
        dMode = debugMode;
        P_NAME = PREFER_NAME;
        if (dMode) {
            System.out.println("Config initialized with PREFER_NAME: " + P_NAME);
        }
    }
    public static String getPreferName() {
        return P_NAME;
    }
    public static int getStartColor() {
        return startColor;
    }
    public static int getCenterColor() {
        return centerColor;
    }
    public static int getEndColor() {
        return endColor;
    }
    public static boolean isDebugMode() {
        return dMode;
    }

    public static synchronized void setProgressColors(int start, int center, int end) {
        if (dMode) {
            System.out.println("Setting progress bar colors: Start = " + start + ", Center = " + center + ", End = " + end);
        }
        startColor = start;
        centerColor = center;
        endColor = end;
    }
}
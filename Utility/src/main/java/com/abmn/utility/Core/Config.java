package com.abmn.utility.Core;

import android.graphics.Color;

public class Config {
    private static boolean dMode = false;
    private static String P_NAME = "abmn_utility";
    private static int P_MODE = 0; // Default preference mode
    private static int startColor = Color.parseColor("#A867F6"); // Default start color
    private static int centerColor = Color.parseColor("#B593DF"); // Default center color
    private static int endColor = Color.parseColor("#6D29BF"); // Default end color

    // Initialize the base configuration
    public static synchronized void init(String PREFER_NAME, int PRIVATE_MODE, boolean debugMode) {
        dMode = debugMode;
        P_NAME = PREFER_NAME;
        P_MODE = PRIVATE_MODE;
        if (dMode) {
            System.out.println("Config initialized with PREFER_NAME: " + P_NAME + ", PRIVATE_MODE: " + P_MODE);
        }
    }

    public static String getPreferName() {
        return P_NAME;
    }

    public static int getPrivateMode() {
        return P_MODE;
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

    // Method to set progress bar colors
    public static synchronized void setProgressColors(int start, int center, int end) {
        if (dMode) {
            System.out.println("Setting progress bar colors: Start = " + start + ", Center = " + center + ", End = " + end);
        }
        startColor = start;
        centerColor = center;
        endColor = end;
    }
}

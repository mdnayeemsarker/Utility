package com.abmn.utility.SharedPref;

import android.app.Activity;
import android.content.SharedPreferences;

public class USession {
    public static final String PREFER_NAME = "abmn_utility";
    final int PRIVATE_MODE = 0;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Activity activity;

    public USession(Activity activity) {
        try {
            this.activity = activity;
            pref = activity.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
            editor = pref.edit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setData(String id, String val) {
        editor.putString(id, val);
        editor.commit();
    }

    public String getData(String id) {
        return pref.getString(id, "");
    }

    public void setBoolean(String id, boolean val) {
        editor.putBoolean(id, val);
        editor.commit();
    }

    public boolean getBoolean(String id) {
        return pref.getBoolean(id, false);
    }
    public void setInteger(String id, int val) {
        editor.putInt(id, val);
        editor.commit();
    }

    public int getInteger(String id) {
        return pref.getInt(id, 0);
    }
    public void setDouble(String id, double val) {
        long longVal = Double.doubleToRawLongBits(val);
        editor.putLong(id, longVal);
        editor.commit();
    }

    public double getDouble(String id) {
        long longVal = pref.getLong(id, 0);
        return Double.longBitsToDouble(longVal);
    }
    public void setFloat(String id, float val) {
        editor.putFloat(id, val);
        editor.commit();
    }

    public float getFloat(String id) {
        return pref.getFloat(id, 0);
    }
    public void clearData() {
        editor.clear();
        editor.commit();
    }
}

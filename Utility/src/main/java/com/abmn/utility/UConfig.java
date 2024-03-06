package com.abmn.utility;

import static android.content.Context.CONNECTIVITY_SERVICE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AlertDialog;

public class UConfig {
    public static final String PREFER_NAME = "abmn_utility";
    final int PRIVATE_MODE = 0;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Activity activity;
    public UConfig(Activity activity) {
        try {
            this.activity = activity;
            pref = activity.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
            editor = pref.edit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifiNetwork != null && wifiNetwork.isConnected()) || (mobileNetwork != null && mobileNetwork.isConnected());
    }

    public void isConnectedAlert(String title, String message) {
        if (title.equals("") || title.equals("null")) {
            title = "Alert by MD NAYEEM SARKER";
        }
        if (message.equals("") || message.equals("null")) {
            message = "This is an alert message from MD NAYEEM SARKER, If you give null or empty then it show default message";
        }
        if (!isConnected()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton("Retry", (dialog, which) -> dialog.dismiss())
                    .setNegativeButton("cancel", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
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
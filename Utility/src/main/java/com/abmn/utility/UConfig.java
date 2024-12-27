package com.abmn.utility;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.INPUT_METHOD_SERVICE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;

import com.abmn.utility.Core.Config;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Objects;

public class UConfig {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Context context;
    @SuppressLint("WrongConstant")
    public UConfig(Context context) {
        try {
            this.context= context;
            pref = context.getSharedPreferences(Config.getPreferName(), Config.getPrivateMode());
            editor = pref.edit();
        } catch (Exception e) {
            if (Config.isDebugMode())
                Log.d("config", Objects.requireNonNull(e.getMessage()));
        }
    }

    public Boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifiNetwork != null && wifiNetwork.isConnected()) || (mobileNetwork != null && mobileNetwork.isConnected());
    }

    public void isConnectedAlert(String title, String message) {
        if (title.isEmpty() || title.equals("null")) {
            title = "Alert by MD NAYEEM SARKER";
        }
        if (message.isEmpty() || message.equals("null")) {
            message = "This is an alert message from MD NAYEEM SARKER, If you give null or empty then it show default message";
        }
        if (!isConnected()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
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

    public void setJSONArray(String key, JSONArray list) {
        editor.putString(key, list.toString());
        editor.apply();
        editor.commit();
    }

    public JSONArray getJSONArray(String key) {
        String json = pref.getString(key, null);
        try {
            return new JSONArray(json);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public void hideKeyboard(View root) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(root.getApplicationWindowToken(), 0);
        } catch (Exception e) {
            if (Config.isDebugMode())
                Log.d("keyboardHideEx", Objects.requireNonNull(e.getMessage()));
        }
    }
}
package com.abmn.utility.Internet;

import static android.content.Context.CONNECTIVITY_SERVICE;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AlertDialog;

public class UConfig {
    Activity activity;
    public UConfig(Activity activity) {
        this.activity = activity;
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

}
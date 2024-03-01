package com.abmn.utility.Internet;

import static android.content.Context.CONNECTIVITY_SERVICE;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UConfig {

    public static Boolean isConnected(final Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifiNetwork != null && wifiNetwork.isConnected()) || (mobileNetwork != null && mobileNetwork.isConnected());
    }

    public static void isConnectedAlert(Activity activity, String title, String message) {
        if (title.equals("") || title.equals("null")) {
            title = "Alert by MD NAYEEM SARKER";
        }
        if (message.equals("") || message.equals("null")) {
            message = "This is an alert message from MD NAYEEM SARKER, If you give null or empty then it show default message";
        }
        if (!isConnected(activity)) {
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

    public static void requestToAPI(final VolleyCallback callback, int method, Activity activity, String url, Map<String, String> params, boolean isProgress) {
        if (ProgressDisplay.mProgressBar != null) {
            ProgressDisplay.mProgressBar.setVisibility(View.GONE);
        }
        final ProgressDisplay progressDisplay = new ProgressDisplay(activity);
        progressDisplay.hideProgress();
        if (isProgress)
            progressDisplay.showProgress();

        RequestQueue queue = Volley.newRequestQueue(activity);

        StringRequest stringRequest = new StringRequest(method, url,
                response -> {
                    callback.onSuccess(true, response);
                    if (isProgress)
                        progressDisplay.hideProgress();
                },
                error -> {
                    callback.onSuccess(false, error.getMessage());
                    if (isProgress)
                        progressDisplay.hideProgress();
                }) {
            @Override
            public Map<String, String> getHeaders() {
                return new HashMap<>();
            }

            @NonNull
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
        queue.add(stringRequest);
    }

    public static void requestToAPI(final VolleyCallback callback, Activity activity, String url, Map<String, String> params, Map<String, String> fileParams, boolean isProgress) {
        if (ProgressDisplay.mProgressBar != null) {
            ProgressDisplay.mProgressBar.setVisibility(View.GONE);
        }
        final ProgressDisplay progressDisplay = new ProgressDisplay(activity);
        progressDisplay.hideProgress();
        if (isProgress)
            progressDisplay.showProgress();
        RequestQueue queue = Volley.newRequestQueue(activity);

        VolleyMultiPartRequest multipartRequest = new VolleyMultiPartRequest(url,
                response -> {
                    callback.onSuccess(true, response);
                    if (isProgress)
                        progressDisplay.hideProgress();
                },
                error -> {
                    callback.onSuccess(false, error.getMessage());
                    if (isProgress)
                        progressDisplay.hideProgress();
                }) {

            @Override
            public Map<String, String> getHeaders() {
                return params;
            }

            @Override
            public Map<String, String> getDefaultParams() {
                return params;
            }

            @Override
            public Map<String, String> getFileParams() {
                return fileParams;
            }

        };
        multipartRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(multipartRequest);
    }
}
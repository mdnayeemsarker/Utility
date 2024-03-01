package com.abmn.utility.Internet;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class UConfig {

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static void isConnectedAlert(Context context, String title, String message) {
        if (TextUtils.isEmpty(title)) {
            title = "Alert";
        }
        if (TextUtils.isEmpty(message)) {
            message = "No internet connection available.";
        }
        if (!isConnected(context)) {
            new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Retry", null)
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }

    public static void requestToVolley(final VolleyCallback callback, int method, Activity activity, String url, Map<String, String> params, boolean isProgress) {
        if (ProgressDisplay.mProgressBar != null) {
            ProgressDisplay.mProgressBar.setVisibility(View.GONE);
        }
        final ProgressDisplay progressDisplay = new ProgressDisplay(activity);
        progressDisplay.hideProgress();
        if (isProgress) {
            progressDisplay.showProgress();
        }
        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        StringRequest stringRequest = new StringRequest(method, url,
                response -> {
                    callback.onSuccess(true, response);
                    if (isProgress) {
                        progressDisplay.hideProgress();
                    }
                }, error -> callback.onSuccess(false, error.getMessage())) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    public static void requestToVolley(final VolleyCallback callback, Activity activity, String url, Map<String, String> params, Map<String, String> fileParams, boolean isProgress) {
        if (ProgressDisplay.mProgressBar != null) {
            ProgressDisplay.mProgressBar.setVisibility(View.GONE);
        }
        final ProgressDisplay progressDisplay = new ProgressDisplay(activity);
        progressDisplay.hideProgress();
        if (isProgress) {
            progressDisplay.showProgress();
        }
        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());

        VolleyMultiPartRequest multipartRequest = new VolleyMultiPartRequest(url,
                response -> {
                    callback.onSuccess(true, response);
                    if (isProgress) {
                        progressDisplay.hideProgress();
                    }
                },
                error -> {
                    callback.onSuccess(false, error.getMessage());
                    if (isProgress) {
                        progressDisplay.hideProgress();
                    }
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
        multipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(multipartRequest);
    }


}

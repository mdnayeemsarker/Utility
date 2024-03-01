package com.abmn.utility.UI;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class USnackBar {
    public void Snackbar(View rootView, String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }
    public void SnackbarWithAction(View rootView, String message, String title, Activity activity, Class<? extends Activity> targetActivityClass) {
        if (rootView == null || message == null || title == null || activity == null || targetActivityClass == null) {
            return;
        }

        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).setAction(title, v -> {
            try {
                activity.startActivity(new Intent(activity, targetActivityClass));
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                Snackbar(rootView, "Activity not found");
            }
        }).show();
    }
}

package com.abmn.utility.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RotateDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.abmn.utility.Core.Config;
import com.android.library.utility.R;

import java.util.Objects;

public class ProgressDisplay {
    @SuppressLint("StaticFieldLeak")
    private static ProgressBar mProgressBar;
    private final Context mContext;

    @SuppressLint("UseCompatLoadingForDrawables")
    public ProgressDisplay(Context context) {
        this.mContext = context;

        try {
            // Assuming the root layout is provided for non-Activity contexts
            if (context instanceof Activity) {
                ViewGroup layout = ((Activity) context).findViewById(android.R.id.content);
                initializeProgressBar(layout);
            } else {
                throw new IllegalArgumentException("Context must be an Activity or provide a valid ViewGroup root.");
            }
        } catch (Exception e) {
            if (Config.isDebugMode())
                Log.d("ABMN_Progress_Dialog", Objects.requireNonNull(e.getMessage()));
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initializeProgressBar(ViewGroup layout) {
        mProgressBar = new ProgressBar(mContext, null, android.R.attr.progressBarStyle);
        mProgressBar.setIndeterminateDrawable(mContext.getDrawable(R.drawable.custom_progress_dialog));
        mProgressBar.setIndeterminate(true);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );

        RelativeLayout rl = new RelativeLayout(mContext);
        rl.setGravity(Gravity.CENTER);
        rl.addView(mProgressBar);
        layout.addView(rl, params);
        hideProgress();
        applyProgressColors();
    }

    public void showProgress() {
        if (mProgressBar != null && mProgressBar.getVisibility() == View.GONE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgress() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private void applyProgressColors() {
        try {
            @SuppressLint("UseCompatLoadingForDrawables") RotateDrawable drawable = (RotateDrawable) mContext.getDrawable(R.drawable.custom_progress_dialog);
            if (drawable != null) {
                GradientDrawable gradient = (GradientDrawable) drawable.getDrawable();
                if (gradient != null) {
                    gradient.setColors(new int[]{Config.getStartColor(), Config.getCenterColor(), Config.getEndColor()});
                    mProgressBar.setIndeterminateDrawable(drawable);
                }
            }
        } catch (Exception e) {
            if (Config.isDebugMode())
                Log.d("ABMN_Progress_Dialog", Objects.requireNonNull(e.getMessage()));
        }
    }
}
package com.abmn.utility.Internet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.library.utility.R;

public class ProgressDisplay {

    @SuppressLint("StaticFieldLeak")
    public static ProgressBar mProgressBar;

    @SuppressLint("UseCompatLoadingForDrawables")
    public ProgressDisplay(Activity activity) {
        try {
            ViewGroup layout = (ViewGroup) (activity).findViewById(android.R.id.content).getRootView();

            mProgressBar = new ProgressBar(activity, null, android.R.attr.progressBarStyle);
            mProgressBar.setIndeterminateDrawable(activity.getDrawable(R.drawable.custom_progress_dialog));
            mProgressBar.setIndeterminate(true);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

            RelativeLayout rl = new RelativeLayout(activity);
            rl.setGravity(Gravity.CENTER);
            rl.addView(mProgressBar);
            layout.addView(rl, params);
            hideProgress();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showProgress() {
        if (mProgressBar.getVisibility() == View.GONE) mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

}

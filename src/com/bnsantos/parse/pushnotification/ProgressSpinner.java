package com.bnsantos.parse.pushnotification;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by bruno on 4/6/14.
 */
public class ProgressSpinner {
    public static String TAG = ProgressSpinner.class.getName();
    private LinearLayout loadingLayout;
    private LinearLayout contentLayout;
    private int animTime;

    public ProgressSpinner(LinearLayout loadingLayout, LinearLayout contentLayout, int animTime) {
        super();
        Log.d(TAG, "Progress spinner started [" + loadingLayout.getId() + ", " + contentLayout.getId() + "]");
        this.loadingLayout = loadingLayout;
        this.contentLayout = contentLayout;
        this.animTime = animTime;
    }

    public void show(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Log.d(TAG, "Animation for " + Build.VERSION_CODES.HONEYCOMB_MR2);
            loadingLayout.setVisibility(View.VISIBLE);
            setAnimation(loadingLayout, show, true);

            contentLayout.setVisibility(View.GONE);
            setAnimation(contentLayout, show, false);

        } else {
            Log.d(TAG, "Animation for version LT " + Build.VERSION_CODES.HONEYCOMB_MR2);
            loadingLayout.setVisibility(show ? View.VISIBLE : View.GONE);
            contentLayout.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private int setAlpha(boolean show, boolean progressLayout) {
        if (progressLayout) {
            return show ? 1 : 0;
        } else {
            return show ? 0 : 1;
        }
    }

    private int setVisibility(boolean show, boolean progressLayout) {
        if (progressLayout) {
            return show ? View.VISIBLE : View.GONE;
        } else {
            return show ? View.GONE : View.VISIBLE;
        }
    }

    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setAnimation(final LinearLayout progress, boolean show, boolean progressLayout) {

        final int alpha = setAlpha(show, progressLayout);
        final int visibility = setVisibility(show, progressLayout);
        progress.animate().setDuration(animTime).alpha(alpha).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progress.setVisibility(visibility);
            }
        });
    }
}

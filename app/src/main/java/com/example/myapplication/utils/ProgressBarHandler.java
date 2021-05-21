package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class ProgressBarHandler {

    private static ProgressBar mProgressBar;
    public ProgressBarHandler(Context context) {
        ViewGroup layout = (ViewGroup) ((Activity) context).findViewById(android.R.id.content)
                .getRootView();

        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyle);
        mProgressBar.setIndeterminate(true);
      mProgressBar.setProgress(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            mProgressBar.setMaxHeight(50);
            mProgressBar.setMaxWidth(50);
        }


        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);

        RelativeLayout rl = new RelativeLayout(context);

        rl.setGravity(Gravity.CENTER);
        rl.addView(mProgressBar);

        layout.addView(rl, params);

        hide();

    }
    public static void show() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public static void hide() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}

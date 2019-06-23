package com.eywa_kitchen.EywaSmartBar;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED;

public class MainActivity extends AppCompatActivity {


    private View llBottomSheet;
    private BottomSheetBehavior Bar;
    private FrameLayout SwipeZone;
    private Intent service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_beh);

        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, 3);
        service = new Intent(this, UIbarService.class);
        service.setFlags(FLAG_ACTIVITY_NEW_TASK);
      //  startService(service);
    }




}

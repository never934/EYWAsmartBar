package com.eywa_kitchen.EywaSmartBar;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;

import static android.support.design.widget.BottomSheetBehavior.STATE_DRAGGING;
import static android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED;
import static android.support.design.widget.BottomSheetBehavior.STATE_HIDDEN;
import static android.support.design.widget.BottomSheetBehavior.STATE_SETTLING;

public class MyAccessibilityService extends AccessibilityService {



    private WindowManager windowManager;
    private WindowManager BarManager;
    private WindowManager.LayoutParams params;
    private WindowManager.LayoutParams BarParams;
    private FrameLayout rootView;
    private View llBottomSheet;
    private BottomSheetBehavior HomeBar;

    @Override
    protected void onServiceConnected() {
        LineState();
    }

    private void LineState(){
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        rootView = new FrameLayout(this);

        params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                5, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_SPLIT_TOUCH|
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|
                        WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS|
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.BOTTOM;

        windowManager.addView(rootView, params);
        rootView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                Log.e("SYSTEM ", "TO TOP");
                windowManager.removeView(rootView);
                openBar();
            }
            public void onSwipeRight() {
                Log.e("SYSTEM ", "TO RIGHT");
            }
            public void onSwipeLeft() {
                Log.e("SYSTEM ", "TO LEFT");
            }
            public void onSwipeBottom() {
                Log.e("SYSTEM ", "TO BOTTOM");
            }

        });
    }

    private void openBar(){
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        rootView = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.act_beh, null);

        llBottomSheet = rootView.findViewById(R.id.Homebar);
        HomeBar = BottomSheetBehavior.from(llBottomSheet);

        params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_SPLIT_TOUCH|
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|
                        WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS|
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        // Задаем позиции для нашего Layout
        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = 0;
        // Отображаем наш Layout
        windowManager.addView(rootView, params);

        HomeBar.setState(STATE_HIDDEN);
        HomeBar.setState(STATE_EXPANDED);

        HomeBar.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    HomeBar.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                String st = String.valueOf(newState);
                Log.e("AAA ", st);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }

        });


        llBottomSheet.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeBottom() {
                Log.e("SYSTEM ", "TO BOTTOM");
                closeBar();
            }
        });
    }

    private void closeBar(){
        HomeBar.setState(STATE_HIDDEN);
        windowManager.removeViewImmediate(rootView);
        LineState();
    }










    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override
    public void onInterrupt() {

    }
}

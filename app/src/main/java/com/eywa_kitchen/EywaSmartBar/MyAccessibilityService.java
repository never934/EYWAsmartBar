package com.eywa_kitchen.EywaSmartBar;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;

public class MyAccessibilityService extends AccessibilityService {

    float upX;
    float downX;

    @Override
    protected void onServiceConnected() {
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        FrameLayout layout = new FrameLayout(this);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
                        WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS|
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP;

        windowManager.addView(layout, params);
        layout.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                Log.e("SYSTEM ", "TO TOP");
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

    private void swipeToRight(){
        Log.e("SYSTEM ", "TO RIGHT");
    }

    private void swipeToLeft(){
        Log.e("SYSTEM ", "TO LEFT");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected boolean onGesture(int gestureId) {
        Log.e("GESTURE", "called");
        return super.onGesture(gestureId);
    }
}

package com.eywa_kitchen.EywaSmartBar;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.FingerprintGestureController;
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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import static android.support.design.widget.BottomSheetBehavior.STATE_DRAGGING;
import static android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED;
import static android.support.design.widget.BottomSheetBehavior.STATE_HIDDEN;
import static android.support.design.widget.BottomSheetBehavior.STATE_SETTLING;
import static xdroid.toaster.Toaster.toast;

public class MyAccessibilityService extends AccessibilityService {



    private WindowManager windowManager;
    private WindowManager BarManager;
    private WindowManager.LayoutParams params;
    private WindowManager.LayoutParams BarParams;
    private FrameLayout rootView;
    private View llBottomSheet;
    private BottomSheetBehavior HomeBar;
    private boolean BarOpened=false;
    private int embedded_forwarding = 0;

    @Override
    protected void onServiceConnected() {
        init();
    }

    @Override
    protected boolean onGesture(int gestureId) {
        String msg = String.valueOf(gestureId);
        Log.e("ASY", msg);
        return super.onGesture(gestureId);
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        Log.e("ACY", "clicked");
        switch (keyCode) {
            case 72:
                embedded_forwarding++;
                if(embedded_forwarding==2) {
                    Log.e("ACY", "Bar");
                    embedded_forwarding=0;
                    ControlBar();
                }
                break;
        }
        return super.onKeyEvent(event);
    }


    private void init(){
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        rootView = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.act_beh, null);

        llBottomSheet = rootView.findViewById(R.id.Homebar);
        HomeBar = BottomSheetBehavior.from(llBottomSheet);
        params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_SPLIT_TOUCH|
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
                        WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS|
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.BOTTOM;

        windowManager.addView(rootView, params);
        HomeBar.setState(STATE_HIDDEN);
    }

    private void ControlBar(){
        if(BarOpened){
            HomeBar.setState(STATE_HIDDEN);
            UnSetBarView();
            BarOpened=false;
        }else{
            HomeBar.setState(STATE_EXPANDED);
            SetBarView();
            BarOpened=true;
        }
    }

    private void SetBarView(){
        params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_SPLIT_TOUCH|
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|
                        WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS|
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        windowManager.updateViewLayout(rootView,params);

        HomeBar.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                Log.e("ccc","State changed");
                if(i==STATE_HIDDEN)UnSetBarView();
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                Log.e("ccc","Slide changed");
            }
        });
    }

    private void UnSetBarView(){
        params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_SPLIT_TOUCH|
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
                        WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS|
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        windowManager.updateViewLayout(rootView,params);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override
    public void onInterrupt() {

    }
}

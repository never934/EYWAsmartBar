package com.eywa_kitchen.EywaSmartBar.Service;

import android.accessibilityservice.AccessibilityService;

import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;

import static android.support.design.widget.BottomSheetBehavior.STATE_DRAGGING;
import static android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED;
import static android.support.design.widget.BottomSheetBehavior.STATE_HALF_EXPANDED;
import static android.support.design.widget.BottomSheetBehavior.STATE_HIDDEN;
import static android.support.design.widget.BottomSheetBehavior.STATE_SETTLING;

public class MyAccessibilityService extends AccessibilityService implements AccessibilityContract.View {



    private WindowManager windowManager;
    private WindowManager.LayoutParams params;
    private FrameLayout rootView;
    private View llBottomSheet;
    private BottomSheetBehavior HomeBar;
    private AccessibilityContract.Presenter Presenter;

    @Override
    protected void onServiceConnected() {
        initPresenter();
        init();
        Presenter.SetActivityParams();
        SetListeners();
    }

    private void initPresenter(){
        Presenter = new AccessibilityPresenter(this);
    }
    private void init(){
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        rootView = (FrameLayout) LayoutInflater.from(this).inflate(com.eywa_kitchen.EywaSmartBar.R.layout.act_beh, null);
        llBottomSheet = rootView.findViewById(com.eywa_kitchen.EywaSmartBar.R.id.Homebar);
        HomeBar = BottomSheetBehavior.from(llBottomSheet);
        HomeBar.setState(STATE_HIDDEN);
    }

    private void SetListeners(){
        HomeBar.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                Log.e("ccc","State changed" + i);
                Presenter.BarStateChanged(i);
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                Log.e("ccc","Slide changed");
            }
        });
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        Log.e("ACY", "clicked");
        switch (keyCode) {
            case 72:
                Presenter.BarButtonClicked();
                break;
        }
        return super.onKeyEvent(event);
    }

    @Override
    public void setStartView(WindowManager.LayoutParams params) {
        windowManager.addView(rootView,params);
    }

    @Override
    public void OpenBar() {
        HomeBar.setState(STATE_EXPANDED);
    }

    @Override
    public void HideBar() {
        HomeBar.setState(STATE_HIDDEN);
    }

    @Override
    public void setView(WindowManager.LayoutParams params){
        windowManager.updateViewLayout(rootView,params);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override
    public void onInterrupt() {

    }
}

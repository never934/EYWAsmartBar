package com.eywa_kitchen.EywaSmartBar.Service;

import android.graphics.PixelFormat;
import android.support.design.widget.BottomSheetBehavior;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

public class AccessibilityPresenter implements AccessibilityContract.Presenter {

    private AccessibilityContract.View View;
    private int embedded_forwarding = 0;
    private boolean BarOpened=false;
    private int BAR_OPENED=1;
    private int BAR_CLOSED=0;

    public AccessibilityPresenter(AccessibilityContract.View View) {
        this.View = View;
    }

    @Override
    public void SetActivityParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_SPLIT_TOUCH|
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
                        WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS|
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.BOTTOM;
        View.setStartView(params);
    }

    @Override
    public void BarButtonClicked() {
        embedded_forwarding++;
        if(embedded_forwarding==2) {
            Log.e("ACY", "Bar");
            embedded_forwarding=0;
            ControlBar();
        }
    }

    @Override
    public void BarStateChanged(int State) {
        if(State == 4)View.HideBar();
        if(State == BottomSheetBehavior.STATE_HIDDEN)
        {
            View.setView(getParams(BAR_CLOSED));
            BarOpened=false;
        }

    }

    private void ControlBar(){
        if(BarOpened){
            BarOpened=false;
            View.HideBar();
            View.setView(getParams(BAR_CLOSED));
        }else{
            BarOpened=true;
            View.OpenBar();
            View.setView(getParams(BAR_OPENED));
        }
    }

    private WindowManager.LayoutParams getParams(int BarState){
        WindowManager.LayoutParams params;
        if(BarState==BAR_OPENED){
            params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN |
                            WindowManager.LayoutParams.FLAG_SPLIT_TOUCH|
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|
                            WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS|
                            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                    PixelFormat.TRANSLUCENT);
            return params;
        }else{
            params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN |
                            WindowManager.LayoutParams.FLAG_SPLIT_TOUCH|
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
                            WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS|
                            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                    PixelFormat.TRANSLUCENT);
            return params;
        }
    }
}

package com.eywa_kitchen.EywaSmartBar.Service;

import android.view.WindowManager;

public interface AccessibilityContract {

    interface View{
        void OpenBar();
        void HideBar();
        void setView(WindowManager.LayoutParams params);
        void setStartView(WindowManager.LayoutParams params);
    }

    interface Presenter{
        void SetActivityParams();
        void BarButtonClicked();
        void BarStateChanged(int State);
    }
}

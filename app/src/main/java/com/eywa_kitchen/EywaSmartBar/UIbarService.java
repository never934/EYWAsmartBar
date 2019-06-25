package com.eywa_kitchen.EywaSmartBar;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class UIbarService extends Service {
    private LinearLayout rootView;
    private WindowManager manager;
    private WindowManager.LayoutParams params;
    private WindowManager windowManager;
    private int Width=1080,Height=1920;
    private Button knopa;
    private View AppGrab;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Service","STARTED");
      //  initScreenUtils();
        SetLayoutInService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    private void SetLayoutInService(){
        manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT, // Ширина экрана
                WindowManager.LayoutParams.MATCH_PARENT, // Высота экрана
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,  // Говорим, что приложение будет поверх других. В поздних API > 26, данный флаг перенесен на TYPE_APPLICATION_OVERLAY
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,// Необходимо для того чтобы TouchEvent'ы в пустой области передавались на другие приложения
                PixelFormat.TRANSLUCENT); // Само окно прозрачное

        // Задаем позиции для нашего Layout
        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = 0;

        // Отображаем наш Layout
        rootView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.bottom_sheet, null);
        manager.addView(rootView, params);

    }

}

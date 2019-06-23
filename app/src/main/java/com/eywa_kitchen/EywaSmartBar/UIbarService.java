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
        SetListener();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    private void SetLayoutInService(){
        manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        params = new WindowManager.LayoutParams(
                Width, // Ширина экрана
                Height, // Высота экрана
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,  // Говорим, что приложение будет поверх других. В поздних API > 26, данный флаг перенесен на TYPE_APPLICATION_OVERLAY
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,// Необходимо для того чтобы TouchEvent'ы в пустой области передавались на другие приложения
                PixelFormat.TRANSLUCENT); // Само окно прозрачное

        // Задаем позиции для нашего Layout
        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = 0;

        // Отображаем наш Layout
        rootView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.bottom_sheet, null);
        AppGrab = rootView.findViewById(R.id.grab);
        manager.addView(rootView, params);

    }

    private void SetListener(){
        AppGrab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.height = Math.max(-((int) motionEvent.getRawY()), ScreenUtils.convertDpToPx(UIbarService.this, 5));
                        manager.updateViewLayout(rootView, params);
                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                }
                return true;
            }
        });
    }

}

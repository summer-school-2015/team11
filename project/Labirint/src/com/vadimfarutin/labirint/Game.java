package com.vadimfarutin.labirint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.*;



public class Game extends Activity implements SensorEventListener, View.OnClickListener{
    ImageViewMy ball;
    SensorManager sm;
    Sensor accel;
    FrameLayout main;
    Bitmap bitmap;
    TextView text;
    Button newgame;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,accel,0);

        main = (FrameLayout)findViewById(R.id.game);
        System.out.println(main);
        ball = new ImageViewMy(this);
        FrameLayout.LayoutParams imageViewLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        ball.setLayoutParams(imageViewLayoutParams);
        main.addView(ball);
        switch (MyActivity.number)
        {
            case "0":
                ball.setBackgroundResource(R.drawable.m1);
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m1);
                break;
            case "1":
                ball.setBackgroundResource(R.drawable.m2);
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m2);
                break;
        }
        ball.starttime = System.currentTimeMillis();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] > 0)
        {
            if (ball.getShiftx() - 5 > 0)
                ball.changexy(-5, 0);
        }
        else
        {
            if (ball.getShiftx() + 5 < ball.getWidth() - 87)
                ball.changexy(5, 0);
        }
        if (event.values[1] < 0)
        {
            if (ball.getShifty() - 5 > 0)
                ball.changexy(0, -5);
        }
        else
        {
            if (ball.getShifty() + 5 < ball.getHeight() - 87)
                ball.changexy(0, 5);
        }
        int clr = mapColor();

        ball.winLose(clr);
        if (ball.fl != 0)
        {
            sm.unregisterListener(this);
            setContentView(R.layout.result);
            text = (TextView)findViewById(R.id.result);
            newgame = (Button)findViewById(R.id.newgame);
            newgame.setOnClickListener(this);
            if (ball.fl == 1)
            {
                text.setText("You win! Result: " + ball.res);

            }
            else
            {
                text.setText("You are loser! Result: "+ball.res);
            }
        }
        ball.invalidate();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    public int mapColor()
    {
        int x = ball.getShiftx();
        int y = ball.getShifty();
        int color = bitmap.getPixel(x + ball.bmp.getWidth() / 2, y + ball.bmp.getHeight() / 2);
        return color;
    }

    @Override
    public void onClick(View v) {

        if(v instanceof Button) {
            finish();
        }
    }

}





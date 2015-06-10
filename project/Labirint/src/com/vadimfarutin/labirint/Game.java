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

import java.nio.Buffer;


public class Game extends Activity implements SensorEventListener, View.OnClickListener{
    static int default_w = 720;
    static int default_h = 1280;
    ImageViewMy ball;
    SensorManager sm;
    Sensor accel;
    FrameLayout main;
    Bitmap bitmap0, bitmap;
    TextView text;
    Button newgame;
    int speed;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,accel,0);

        main = (FrameLayout)findViewById(R.id.game);
        ball = new ImageViewMy(this);
        FrameLayout.LayoutParams imageViewLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        ball.setLayoutParams(imageViewLayoutParams);
        main.addView(ball);
        switch (MyActivity.number)
        {
            case "0":
                ball.setBackgroundResource(R.drawable.m0);
                bitmap0 = BitmapFactory.decodeResource(getResources(), R.drawable.m0);
                break;
            case "1":
                ball.setBackgroundResource(R.drawable.m1);
                bitmap0 = BitmapFactory.decodeResource(getResources(), R.drawable.m1);
                break;
        }
        ball.starttime = System.currentTimeMillis();
        speed = 5;
        bitmap = Bitmap.createScaledBitmap(bitmap0, default_w, default_h, true);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] > 0)
        {
            if (ball.getShiftx() - speed >= 0)
                ball.changexy(-speed, 0);
        }
        else
        {
            if (ball.getShiftx() + ball.bmp.getWidth() + speed < main.getWidth())
                ball.changexy(speed, 0);
        }
        if (event.values[1] < 0)
        {
            if (ball.getShifty() - speed >= 0)
                ball.changexy(0, -speed);
        }
        else
        {
            if (ball.getShifty() + ball.bmp.getHeight() + speed < main.getHeight())
                ball.changexy(0, speed);
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
                text.setText("        You win! Result: " + ball.res);
                char []bestchar = MyActivity.results.getString("level "+MyActivity.number, "res").toCharArray();
                long bestlong = 0;
                for (int i = 0; i < bestchar.length; i++)
                {
                    bestlong += bestchar[i];
                    bestlong *= 10;
                }
                bestlong /= 10;
                if (ball.res < bestlong || bestlong == 0)
                    MyActivity.edit.putString("level "+MyActivity.number, ""+ball.res);
            }
            else
            {
                text.setText("                   Game over!");
            }
        }
        ball.invalidate();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    public int mapColor()
    {
        if ((bitmap.getWidth() != main.getWidth() ||
                bitmap.getHeight() != main.getHeight()) &&
                (ball.getShiftx() != 0 || ball.getShifty() != 0))
            bitmap = Bitmap.createScaledBitmap(bitmap0, main.getWidth(), main.getHeight(), true);

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





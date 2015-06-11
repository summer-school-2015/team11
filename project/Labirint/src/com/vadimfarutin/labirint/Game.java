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
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

public class Game extends Activity implements SensorEventListener, View.OnClickListener{
    static int default_w = 720;
    static int default_h = 1280;
    ImageViewMy ball;
    SensorManager sm;
    Sensor accel;
    public FrameLayout main;
    Bitmap bitmap0, bitmap;
    TextView text, newrecord, delayText;
    Button newgame, restart;
    int speed;
    int speedTmp;
    boolean started;
    long startDelay;
    long endDelay;
    long bestlong;
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
                speed = 5;
                break;
            case "1":
                ball.setBackgroundResource(R.drawable.m1);
                bitmap0 = BitmapFactory.decodeResource(getResources(), R.drawable.m1);
                speed = 10;
                break;
            case "2":
                ball.setBackgroundResource(R.drawable.m2);
                bitmap0 = BitmapFactory.decodeResource(getResources(), R.drawable.m2);
                speed = 1;
                break;
        }
        bitmap = Bitmap.createScaledBitmap(bitmap0, default_w, default_h, true);
        ball.starttime = System.currentTimeMillis();
        started = false;
        ball.invalidate();
        startDelay = System.currentTimeMillis();
        speedTmp = speed;
        ball.w = default_w;
        ball.h = default_h;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        endDelay = System.currentTimeMillis();

        if (endDelay - startDelay < 3000)
            speed =  0;
        else
            speed = speedTmp;
        if (event.values[0] > 0) {
                if (ball.getShiftx() - speed >= 0)
                    ball.changexy(-speed, 0);
            } else {
                if (ball.getShiftx() + ball.bmp.getWidth() + speed < main.getWidth())
                    ball.changexy(speed, 0);
            }
            if (event.values[1] < 0) {
                if (ball.getShifty() - speed >= 0)
                    ball.changexy(0, -speed);
            } else {
                if (ball.getShifty() + ball.bmp.getHeight() + speed < main.getHeight())
                    ball.changexy(0, speed);
            }
            speed = speedTmp;
            ball.winLose(mapColor());
            if (ball.fl != 0) {
                sm.unregisterListener(this);
                setContentView(R.layout.result);
                text = (TextView) findViewById(R.id.result);
                newgame = (Button) findViewById(R.id.newgame);
                newgame.setOnClickListener(this);
                restart = (Button) findViewById(R.id.restart);
                restart.setOnClickListener(this);
                if (ball.fl == 1) {
                    text.setGravity(Gravity.CENTER);
                    text.setText("You win! Result: " + ball.res);
                    bestlong = Long.parseLong(MyActivity.results.getString("level " + MyActivity.number, "res"));
                    if (ball.res < bestlong || bestlong == 0) {
                        MyActivity.edit.putString("level " + MyActivity.number, "" + ball.res);
                        Map<String, String> oneLevel = new HashMap<String, String>(2);
                        oneLevel.put("n", "Level " + MyActivity.number);
                        oneLevel.put("r", MyActivity.results.getString("level " + MyActivity.number, "" + ball.res));
                        MyActivity.edit.apply();
                        newrecord = (TextView) findViewById(R.id.newrecord);
                        newrecord.setGravity(Gravity.CENTER);
                        newrecord.setText("New record!");
                    }
                } else {
                    text.setGravity(Gravity.CENTER);
                    text.setText("Game over!");
                }
            }
            ball.invalidate();

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    public int mapColor()
    {
        if ((bitmap.getWidth() != main.getWidth() || bitmap.getHeight() != main.getHeight()) &&
                (ball.getShiftx() != 0 || ball.getShifty() != 0))
        {
            bitmap = Bitmap.createScaledBitmap(bitmap0, main.getWidth(), main.getHeight(), true);
            ball.w = main.getWidth();
            ball.h = main.getHeight();
        }

        return bitmap.getPixel(ball.getShiftx() + ball.bmp.getWidth() / 2, ball.getShifty() + ball.bmp.getHeight() / 2);

    }

    @Override
    public void onClick(View v) {

        if(v instanceof Button) {
            if (v == newgame)
            {
                Intent intent = new Intent(Game.this, MyActivity.class);
                finish();
                startActivity(intent);
            }
            if (v == restart)
            {
                Intent intent = new Intent(Game.this, Game.class);
                finish();
                startActivity(intent);
            }
        }
    }

}





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
    FrameLayout main;
    Bitmap bitmap0, bitmap;
    TextView text, newrecord, pauseText;
    Button newgame, restart, resume;
    int speed, speedTmp;
    long lastStartTime, lastRes;
    long bestlong;
    int lastx, lasty;
    static boolean gameWasPlayed = false, userPause = false, isResultLayout;
    static int soundVolume, musicVolume;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        MyActivity.mediaPlayer.start();
        gameWasPlayed = true;
        soundVolume = MyActivity.soundVolume;
        musicVolume = MyActivity.musicVolume;
        isResultLayout = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        setAccel();
        setBall();
        setMap();
        speedTmp = speed;
        bitmap = Bitmap.createScaledBitmap(bitmap0, default_w, default_h, true);
      //  ball.starttime = System.currentTimeMillis();
        ball.invalidate();
        ball.startDelay = System.currentTimeMillis();
    }
    @Override
    public void onPause() {
        super.onPause();
        MyActivity.mediaPlayer.pause();
        sm.unregisterListener(this);
        if (!userPause && !isResultLayout)
        {
            ball.pauseStart = System.currentTimeMillis();
            setContentView(R.layout.pause);
            resume = (Button) findViewById(R.id.resume);
            resume.setOnClickListener(this);
            pauseText = (TextView)findViewById(R.id.pauseText);
            pauseText.setGravity(Gravity.CENTER);
        }
        userPause = false;
    }
    @Override
    public void onResume()
    {
        super.onResume();
        MyActivity.mediaPlayer.start();
        MyActivity.mediaPlayer.setVolume(musicVolume, musicVolume);
    }
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        moveBall(event);
        ball.winLose(mapColor());
        if (ball.endGame != 0)
            endGame();
        ball.invalidate();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
    void setAccel()
    {
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,accel,0);
    }
    void setBall()
    {
        main = (FrameLayout)findViewById(R.id.game);
        ball = new ImageViewMy(this);
        FrameLayout.LayoutParams imageViewLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        ball.setLayoutParams(imageViewLayoutParams);
        ball.w = default_w;
        ball.h = default_h;
        main.addView(ball);
    }
    void setMap()
    {
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
            case "3":
                ball.setBackgroundResource(R.drawable.m3);
                bitmap0 = BitmapFactory.decodeResource(getResources(), R.drawable.m3);
                speed = 2;
                break;
        }
    }
    void endGame()
    {
        sm.unregisterListener(this);
        setContentView(R.layout.result);
        isResultLayout = true;
        text = (TextView) findViewById(R.id.result);
        newgame = (Button) findViewById(R.id.newgame);
        newgame.setOnClickListener(this);
       // newgame.setBackgroundColor(Color.YELLOW);
        restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(this);
       // restart.setBackgroundColor(Color.GREEN);
        newrecord = (TextView) findViewById(R.id.newrecord);
        newrecord.setGravity(Gravity.CENTER);
        if (MyActivity.results.getString("level " + MyActivity.number, "res").equals("--") ||
                MyActivity.results.getString("level " + MyActivity.number, "res").equals("res"))
            bestlong = 0;
        else
            bestlong = Long.parseLong(MyActivity.results.getString("level " + MyActivity.number, "res"));
        if (ball.endGame == 1)
        {
            ball.res += System.currentTimeMillis() - ball.starttime;
            MyActivity.soundPool.play(MyActivity.soundIdWin, soundVolume, soundVolume, 1, 0, 1);
            text.setGravity(Gravity.CENTER);
            text.setText("You win! Result: " + ball.res);
            if (ball.res < bestlong || bestlong == 0) {
                MyActivity.edit.putString("level " + MyActivity.number, "" + ball.res);
               /* Map<String, String> oneLevel = new HashMap<String, String>(2);
                oneLevel.put("n", "Level " + MyActivity.number);
                oneLevel.put("r", MyActivity.results.getString("level " + MyActivity.number, "" + ball.res));
              */
                MyActivity.edit.commit();
                newrecord.setText("New record!");
            } else
                newrecord.setText("Best result: " + (bestlong == 0 ? "--" : bestlong));
        }
        else
        {
            MyActivity.soundPool.play(MyActivity.soundIdLose, soundVolume, soundVolume, 1, 0, 2);
            text.setGravity(Gravity.CENTER);
            text.setText("Game over!");
            newrecord.setText("Best result: " + (bestlong == 0 ? "--" : bestlong));
        }
    }
    void moveBall(SensorEvent event)
    {
        ball.curtime = System.currentTimeMillis();
        if (ball.curtime - ball.startDelay < 3000)
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
    }
    int mapColor()
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
    public void onClick(View v)
    {
        MyActivity.soundPool.play(MyActivity.soundIdButton, soundVolume, soundVolume, 1, 0, 1);
        if(v instanceof Button) {
            if (v == newgame)
            {
                userPause = true;
                isResultLayout = false;
                Intent intent = new Intent(Game.this, MyActivity.class);
                finish();
                startActivity(intent);
            }
            if (v == restart)
            {
                isResultLayout = false;
                userPause = true;
                Intent intent = new Intent(Game.this, Game.class);
                finish();
                startActivity(intent);
            }
            if (v == resume)
            {
                setContentView(R.layout.game);
                lastx = ball.getShiftx();
                lasty = ball.getShifty();
                lastRes = ball.res;
                if (ball.isStartTimeSet)
                {
                    lastStartTime = ball.starttime;
                    lastRes += ball.pauseStart - lastStartTime;
                }
                setBall();
                setMap();
                sm.registerListener(this,accel,0);
                ball.shiftx = lastx;
                ball.shifty = lasty;
                ball.res = lastRes;
                ball.startDelay = System.currentTimeMillis();
                ball.wasPaused = true;
            }
        }
    }
}





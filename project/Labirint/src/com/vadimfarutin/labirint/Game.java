package com.vadimfarutin.labirint;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Game extends Activity implements SensorEventListener{
    ImageViewMy ball;
    SensorManager sm;
    Sensor accel;
    TextView tx;
    TextView ty;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,accel,SensorManager.SENSOR_DELAY_NORMAL);

        FrameLayout main = (FrameLayout)findViewById(R.id.game);
        ball = new ImageViewMy(this);
        FrameLayout.LayoutParams imageViewLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        ball.setLayoutParams(imageViewLayoutParams);
        main.addView(ball);
        ball.setBackgroundResource(R.drawable.map);
        tx = (TextView)findViewById(R.id.gx);
        ty = (TextView)findViewById(R.id.gy);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        tx.setText("X: "+event.values[0]);
        ty.setText("Y: "+event.values[1]);
        if (event.values[0] > 0)
          ball.changexy(-5, 0);
        else
            ball.changexy(5, 0);
        if (event.values[1] < 0)
            ball.changexy(0, -5);
        else
            ball.changexy(0, 5);
        ball.invalidate();

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}





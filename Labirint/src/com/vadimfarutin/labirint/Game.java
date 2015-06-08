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
import android.widget.Button;
import android.widget.ImageView;

public class Game extends Activity implements SensorEventListener{
    ImageView ball;
    Rect clipBounds;
    SensorManager sm;
    Sensor accel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        ball = (ImageView)findViewById(R.id.ball);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        ball.invalidate();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

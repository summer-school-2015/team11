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

public class Game extends Activity implements View.OnClickListener, SensorEventListener{
    Button pause;
    ImageView iv;
    Rect clipBounds;
    SensorManager sm;
    Sensor accel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        pause = (Button)findViewById(R.id.newgame);
        pause.setOnClickListener(this);
        iv = (ImageView)findViewById(R.id.imageView);



    }



    public void onClick(View v) {

        if( v instanceof Button) {
            Button button = (Button)v;

        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
       float b =  event.values[0];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

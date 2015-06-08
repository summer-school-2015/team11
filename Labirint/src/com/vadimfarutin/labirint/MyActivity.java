package com.vadimfarutin.labirint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity implements View.OnClickListener{
    Button newgame;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        newgame = (Button)findViewById(R.id.newgame);
        newgame.setOnClickListener(this);
    }
    public void onClick(View v) {

        if( v instanceof Button) {
            Button button = (Button)v;
            setContentView(R.layout.game);
        }
    }
}

package com.vadimfarutin.three_buttons;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    public int cl1 = 0, cl2 = 0, cl3 = 0, cnt = 0;
    Button button1, button2, button3;
    TextView text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        text = (TextView)findViewById(R.id.text);

    }

    public void onClick(View v) {

        if( v instanceof Button) {
            Button button = (Button)v;

            int clicks = Integer.parseInt(button.getText().toString());
            clicks++;

            button.setText(String.valueOf(clicks));

            if (button == button1)
                cl1++;
            if (button == button2)
                cl2++;
            if (button == button3)
                cl3++;
            boolean fl = false;
            for (int i = 2; i < cl1 + cl2 + cl3; i++)
                if ((cl1 + cl2 + cl3) % i == 0)
                {
                    fl = true;
                    break;
                }
            if (fl)
            {
                button1.setBackgroundColor(Color.BLUE);
                button2.setBackgroundColor(Color.BLUE);
                button3.setBackgroundColor(Color.BLUE);
                cnt++;
            }
            else
            {
                button1.setBackgroundColor(Color.GREEN);
                button2.setBackgroundColor(Color.GREEN);
                button3.setBackgroundColor(Color.GREEN);
            }
            text.setText(String.valueOf(cnt));
        }
    }
}
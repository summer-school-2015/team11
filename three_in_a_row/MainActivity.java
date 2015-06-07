package com.vadimfarutin.three_in_a_row;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    public boolean first = true;
    Button[] buttons = new Button[17];
    TextView text;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        buttons[1] = (Button)findViewById(R.id.button1);
        buttons[1].setOnClickListener(this);
        buttons[2] = (Button)findViewById(R.id.button2);
        buttons[2].setOnClickListener(this);
        buttons[3] = (Button)findViewById(R.id.button3);
        buttons[3].setOnClickListener(this);
        buttons[4] = (Button)findViewById(R.id.button4);
        buttons[4].setOnClickListener(this);
        buttons[5] = (Button)findViewById(R.id.button5);
        buttons[5].setOnClickListener(this);
        buttons[6] = (Button)findViewById(R.id.button6);
        buttons[6].setOnClickListener(this);
        buttons[7] = (Button)findViewById(R.id.button7);
        buttons[7].setOnClickListener(this);
        buttons[8] = (Button)findViewById(R.id.button8);
        buttons[8].setOnClickListener(this);
        buttons[9] = (Button)findViewById(R.id.button9);
        buttons[9].setOnClickListener(this);
        buttons[10] = (Button)findViewById(R.id.button10);
        buttons[10].setOnClickListener(this);
        buttons[11] = (Button)findViewById(R.id.button11);
        buttons[11].setOnClickListener(this);
        buttons[12] = (Button)findViewById(R.id.button12);
        buttons[12].setOnClickListener(this);
        buttons[13] = (Button)findViewById(R.id.button13);
        buttons[13].setOnClickListener(this);
        buttons[14] = (Button)findViewById(R.id.button14);
        buttons[14].setOnClickListener(this);
        buttons[15] = (Button)findViewById(R.id.button15);
        buttons[15].setOnClickListener(this);
        buttons[16] = (Button)findViewById(R.id.button16);
        buttons[16].setOnClickListener(this);

        text = (TextView)findViewById(R.id.text);
        text.setText("");
    }
    boolean Win ()
    {
		/*horizont*/
        if (buttons[1].getText().toString().equals(buttons[2].getText().toString()) &&
                buttons[3].getText().toString().equals(buttons[2].getText().toString()) &&
                !buttons[2].getText().toString().equals(""))
            return true;
        if (buttons[3].getText().toString().equals(buttons[2].getText().toString()) &&
                buttons[4].getText().toString().equals(buttons[2].getText().toString()) &&
                !buttons[3].getText().toString().equals(""))
            return true;
        if (buttons[5].getText().toString().equals(buttons[6].getText().toString()) &&
                buttons[6].getText().toString().equals(buttons[7].getText().toString()) &&
                !buttons[5].getText().toString().equals(""))
            return true;
        if (buttons[6].getText().toString().equals(buttons[7].getText().toString()) &&
                buttons[8].getText().toString().equals(buttons[7].getText().toString()) &&
                !buttons[8].getText().toString().equals(""))
            return true;
        if (buttons[9].getText().toString().equals(buttons[10].getText().toString()) &&
                buttons[11].getText().toString().equals(buttons[10].getText().toString()) &&
                !buttons[9].getText().toString().equals(""))
            return true;
        if (buttons[10].getText().toString().equals(buttons[12].getText().toString()) &&
                buttons[11].getText().toString().equals(buttons[12].getText().toString()) &&
                !buttons[10].getText().toString().equals(""))
            return true;
        if (buttons[13].getText().toString().equals(buttons[14].getText().toString()) &&
                buttons[15].getText().toString().equals(buttons[14].getText().toString()) &&
                !buttons[13].getText().toString().equals(""))
            return true;
        if (buttons[14].getText().toString().equals(buttons[15].getText().toString()) &&
                buttons[15].getText().toString().equals(buttons[16].getText().toString()) &&
                !buttons[14].getText().toString().equals(""))
            return true;

        /*vertical*/
        if (buttons[1].getText().toString().equals(buttons[5].getText().toString()) &&
                buttons[9].getText().toString().equals(buttons[5].getText().toString()) &&
                !buttons[1].getText().toString().equals(""))
            return true;
        if (buttons[5].getText().toString().equals(buttons[9].getText().toString()) &&
                buttons[13].getText().toString().equals(buttons[9].getText().toString()) &&
                !buttons[5].getText().toString().equals(""))
            return true;
        if (buttons[6].getText().toString().equals(buttons[2].getText().toString()) &&
                buttons[10].getText().toString().equals(buttons[2].getText().toString()) &&
                !buttons[6].getText().toString().equals(""))
            return true;
        if (buttons[6].getText().toString().equals(buttons[14].getText().toString()) &&
                buttons[10].getText().toString().equals(buttons[14].getText().toString()) &&
                !buttons[6].getText().toString().equals(""))
            return true;
        if (buttons[7].getText().toString().equals(buttons[11].getText().toString()) &&
                buttons[3].getText().toString().equals(buttons[11].getText().toString()) &&
                !buttons[3].getText().toString().equals(""))
            return true;
        if (buttons[7].getText().toString().equals(buttons[15].getText().toString()) &&
                buttons[11].getText().toString().equals(buttons[15].getText().toString()) &&
                !buttons[7].getText().toString().equals(""))
            return true;
        if (buttons[4].getText().toString().equals(buttons[12].getText().toString()) &&
                buttons[8].getText().toString().equals(buttons[12].getText().toString()) &&
                !buttons[4].getText().toString().equals(""))
            return true;
        if (buttons[8].getText().toString().equals(buttons[16].getText().toString()) &&
                buttons[12].getText().toString().equals(buttons[16].getText().toString()) &&
                !buttons[8].getText().toString().equals(""))
            return true;

        /*diagonal*/
        if (buttons[6].getText().toString().equals(buttons[3].getText().toString()) &&
                buttons[9].getText().toString().equals(buttons[3].getText().toString()) &&
                !buttons[6].getText().toString().equals(""))
            return true;
        if (buttons[7].getText().toString().equals(buttons[10].getText().toString()) &&
                buttons[13].getText().toString().equals(buttons[10].getText().toString()) &&
                !buttons[7].getText().toString().equals(""))
            return true;
        if (buttons[7].getText().toString().equals(buttons[4].getText().toString()) &&
                buttons[10].getText().toString().equals(buttons[4].getText().toString()) &&
                !buttons[4].getText().toString().equals(""))
            return true;
        if (buttons[14].getText().toString().equals(buttons[8].getText().toString()) &&
                buttons[11].getText().toString().equals(buttons[8].getText().toString()) &&
                !buttons[8].getText().toString().equals(""))
            return true;
        if (buttons[5].getText().toString().equals(buttons[15].getText().toString()) &&
                buttons[10].getText().toString().equals(buttons[15].getText().toString()) &&
                !buttons[15].getText().toString().equals(""))
            return true;
        if (buttons[7].getText().toString().equals(buttons[2].getText().toString()) &&
                buttons[12].getText().toString().equals(buttons[2].getText().toString()) &&
                !buttons[2].getText().toString().equals(""))
            return true;
        if (buttons[1].getText().toString().equals(buttons[11].getText().toString()) &&
                buttons[6].getText().toString().equals(buttons[11].getText().toString()) &&
                !buttons[11].getText().toString().equals(""))
            return true;
        if (buttons[6].getText().toString().equals(buttons[16].getText().toString()) &&
                buttons[11].getText().toString().equals(buttons[16].getText().toString()) &&
                !buttons[16].getText().toString().equals(""))
            return true;


        return false;
    }
    public void onClick(View v) {

        if( v instanceof Button) {
            Button button = (Button)v;

            String used = button.getText().toString();
            if (used.equals(""))
            {
                if (first)
                {
                    first = false;
                    if (button == buttons[1])
                        buttons[1].setText("X");
                    if (button == buttons[2])
                        buttons[2].setText("X");
                    if (button == buttons[3])
                        buttons[3].setText("X");
                    if (button == buttons[4])
                        buttons[4].setText("X");
                    if (button == buttons[5])
                        buttons[5].setText("X");
                    if (button == buttons[6])
                        buttons[6].setText("X");
                    if (button == buttons[7])
                        buttons[7].setText("X");
                    if (button == buttons[8])
                        buttons[8].setText("X");
                    if (button == buttons[9])
                        buttons[9].setText("X");
                    if (button == buttons[10])
                        buttons[10].setText("X");
                    if (button == buttons[11])
                        buttons[11].setText("X");
                    if (button == buttons[12])
                        buttons[12].setText("X");
                    if (button == buttons[13])
                        buttons[13].setText("X");
                    if (button == buttons[14])
                        buttons[14].setText("X");
                    if (button == buttons[15])
                        buttons[15].setText("X");
                    if (button == buttons[16])
                        buttons[16].setText("X");
                    if (Win())
                        text.setText("First win!");
                } else
                {
                    first = true;
                    if (button == buttons[1])
                        buttons[1].setText("O");
                    if (button == buttons[2])
                        buttons[2].setText("O");
                    if (button == buttons[3])
                        buttons[3].setText("O");
                    if (button == buttons[4])
                        buttons[4].setText("O");
                    if (button == buttons[5])
                        buttons[5].setText("O");
                    if (button == buttons[6])
                        buttons[6].setText("O");
                    if (button == buttons[7])
                        buttons[7].setText("O");
                    if (button == buttons[8])
                        buttons[8].setText("O");
                    if (button == buttons[9])
                        buttons[9].setText("O");
                    if (button == buttons[10])
                        buttons[10].setText("O");
                    if (button == buttons[11])
                        buttons[11].setText("O");
                    if (button == buttons[12])
                        buttons[12].setText("O");
                    if (button == buttons[13])
                        buttons[13].setText("O");
                    if (button == buttons[14])
                        buttons[14].setText("O");
                    if (button == buttons[15])
                        buttons[15].setText("O");
                    if (button == buttons[16])
                        buttons[16].setText("O");
                    if (Win())
                        text.setText("Second win!");
                }
            }
        }
    }

}


package com.vadimfarutin.labirint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MyActivity extends Activity {
    ListView list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        list = (ListView) findViewById(R.id.list);
        final String[] levels = new String[]{
                "Level 0", "Level 1"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, levels);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView lvl = (TextView)view;
                String level = lvl.getText().toString();
                if(level.substring(6).equals("0")) {
                    Intent intent = new Intent(MyActivity.this, Game.class);
                    startActivity(intent);
                }
            }
        });

        /*
        newgame = (Button)findViewById(R.id.newgame);
        newgame.setOnClickListener(this);*/
    }
  /*  public void onClick(View v) {

        if( v instanceof Button) {
            Button button = (Button)v;
            Intent intent = new Intent(this, Game.class);
            startActivity(intent);
        }
    }*/
}

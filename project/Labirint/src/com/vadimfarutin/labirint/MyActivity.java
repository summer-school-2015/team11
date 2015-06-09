package com.vadimfarutin.labirint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyActivity extends Activity {
    ListView list;
    int levelsCnt = 20;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        list = (ListView) findViewById(R.id.list);
        /*final String[] levels = new String[]{
                "Level 0", "Level 1", "Level 2", "Level 3", "Level 4", "Level 5"
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
        });*/
        List<Map<String, String>> levels = new ArrayList<Map<String, String>>();
        for (int i = 0; i < levelsCnt; i++)
        {
            Map<String, String> oneLevel = new HashMap<String, String>(2);
            oneLevel.put("n", "Level "+i);
            oneLevel.put("r", "0");
            levels.add(oneLevel);
        }
        SimpleAdapter adapter = new SimpleAdapter(MyActivity.this, levels,
                android.R.layout.simple_list_item_2, new String[] {"n", "r"}, new int[] {android.R.id.text1,
                android.R.id.text2});
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView lvl = (TextView)view.findViewById(android.R.id.text1);
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

package com.vadimfarutin.labirint;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyActivity extends Activity {
    ListView list;
    int levelsCnt = 2;
    public static String number;
    SharedPreferences results;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

      //  results = getPreferences(MODE_PRIVATE);

        list = (ListView) findViewById(R.id.list);
        List<Map<String, String>> levels = new ArrayList<Map<String, String>>();
        for (int i = 0; i < levelsCnt; i++)
        {
            Map<String, String> oneLevel = new HashMap<String, String>(2);
            oneLevel.put("n", "Level "+i);
            oneLevel.put("r", "5");
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

                number = level.substring(6);
                Intent intent = new Intent(MyActivity.this, Game.class);
                startActivity(intent);
            }
        });
    }
}

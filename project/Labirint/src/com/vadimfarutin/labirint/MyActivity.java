package com.vadimfarutin.labirint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyActivity extends Activity implements View.OnClickListener{
    ListView list;
    int levelsCnt = 4;
    static String number;
    static SharedPreferences results;
    static SharedPreferences.Editor edit;
    List<Map<String, String>> levels;
    SimpleAdapter adapter;

    static MediaPlayer mediaPlayer;
    static SoundPool soundPool;
    static int soundIdLose;
    static int soundIdWin;
    static int soundIdButton;
    int MAX_STREAMS = 3;
    static int soundVolume, musicVolume;

    Button reset, music, sound;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        results = getSharedPreferences("results", MODE_PRIVATE);
        edit = results.edit();
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(this);
        //fillList();
        myFillList();
        setMusicAndSound();
    }
 /*   void fillList()
    {
        list = (ListView) findViewById(R.id.list);

        levels = new ArrayList<Map<String, String>>();
        for (int i = 0; i < levelsCnt; i++)
        {
            Map<String, String> oneLevel = new HashMap<String, String>(2);
            oneLevel.put("n", "Level " + i);
            if (results.getString("level "+i, "res").equals("res"))
                oneLevel.put("r", "--");
            else
                oneLevel.put("r", results.getString("level "+i, "res"));
            levels.add(oneLevel);
        }
        adapter = new SimpleAdapter(MyActivity.this, levels,
                android.R.layout.simple_list_item_2, new String[] {"n", "r"}, new int[] {android.R.id.text1,
                android.R.id.text2});
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                soundPool.play(MyActivity.soundIdButton, soundVolume, soundVolume, 1, 0, 1);
                TextView lvl = (TextView) view.findViewById(android.R.id.text1);
                String level = lvl.getText().toString();
                number = level.substring(6);
                Intent intent = new Intent(MyActivity.this, Game.class);
                startActivity(intent);
            }
        });
    }*/
    void myFillList()
    {
        list = (ListView) findViewById(R.id.list);

        levels = new ArrayList<Map<String, String>>();
        for (int i = 0; i < levelsCnt; i++)
        {
            Map<String, String> oneLevel = new HashMap<String, String>(2);
            oneLevel.put("n", "Level " + i);
            if (results.getString("level "+i, "res").equals("res"))
                oneLevel.put("r", "--");
            else
                oneLevel.put("r", results.getString("level "+i, "res"));
            levels.add(oneLevel);
        }
        adapter = new SimpleAdapter(MyActivity.this, levels,
                R.layout.element, new String[] {"n", "r"}, new int[] {R.id.text1,
                R.id.text2});
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                soundPool.play(MyActivity.soundIdButton, soundVolume, soundVolume, 1, 0, 1);
                TextView lvl = (TextView) view.findViewById(R.id.text1);
                String level = lvl.getText().toString();
                number = level.substring(6);
                Intent intent = new Intent(MyActivity.this, Game.class);
                startActivity(intent);
            }
        });
    }
    void setMusicAndSound()
    {
        music = (Button) findViewById(R.id.music);
        music.setOnClickListener(this);
        sound = (Button) findViewById(R.id.sound);
        sound.setOnClickListener(this);
        if (!Game.gameWasPlayed)
        {
            soundVolume = 1;
            musicVolume = 1;
            mediaPlayer = MediaPlayer.create(this, R.raw.background);
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(musicVolume, musicVolume);
            music.setText("Music Off");

            soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
            soundIdLose = soundPool.load(this, R.raw.lose, 1);
            soundIdWin = soundPool.load(this, R.raw.win, 1);
            soundIdButton = soundPool.load(this, R.raw.button, 1);
            sound.setText("Sound Off");
        }
        else
        {
            musicVolume = Game.musicVolume;
            if (musicVolume == 1)
                music.setText("Music Off");
            else
                music.setText("Music On");
            soundVolume = Game.soundVolume;
            if (soundVolume == 1)
                sound.setText("Sound Off");
            else
                sound.setText("Sound On");
        }
    }
    @Override
    public void onPause()
    {
        super.onPause();
        mediaPlayer.pause();
    }
    @Override
    public void onResume()
    {
        super.onResume();
        mediaPlayer.start();
        mediaPlayer.setVolume(musicVolume, musicVolume);
    }
    @Override
    public void onClick(View v)
    {
        if(v instanceof Button) {
            if (v == reset)
            {
                for (int i = 0; i < levelsCnt; i++)
                    edit.putString("level "+i, "--");
                edit.commit();
                myFillList();
            }
            if (v == music)
            {
                if (musicVolume == 1)
                {
                    musicVolume = 0;
                    mediaPlayer.setVolume(musicVolume, musicVolume);
                    music.setText("Music On");
                }
                else
                {
                    musicVolume = 1;
                    mediaPlayer.setVolume(musicVolume, musicVolume);
                    music.setText("Music Off");
                }
            }
            if (v == sound)
            {
                if (soundVolume == 1)
                {
                    soundVolume = 0;
                    sound.setText("Sound On");
                }
                else
                {
                    soundVolume = 1;
                    sound.setText("Sound Off");
                }
            }
        }
        soundPool.play(soundIdButton, soundVolume, soundVolume, 1, 0, 1);
    }
}

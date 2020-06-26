package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dictionaryapp.Common.Common_Level;

import java.io.IOException;

public class Game_Activity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    Button btn_play,btnExit;
    DBmanager dBmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);
        seekBar=findViewById(R.id.seekBar);
        textView=findViewById(R.id.txtLevel);
        btn_play=findViewById(R.id.btnPlay);
        btnExit=findViewById(R.id.btnExitGame);
        dBmanager=new DBmanager(this);
        try {
            dBmanager.createDB();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i==0)
                    textView.setText(Common_Level.MODE.EASY.toString());
                if(i==1)
                    textView.setText(Common_Level.MODE.MEDIUM.toString());
                if(i==2)
                    textView.setText(Common_Level.MODE.HARD.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Game_Activity.this,Playing.class);
                intent.putExtra("MODE",getPlayMode());
                startActivity(intent);
                finish();

            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Game_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    private String  getPlayMode()
    {
        if(seekBar.getProgress()==0)
            return Common_Level.MODE.EASY.toString();
        if(seekBar.getProgress()==0)
            return Common_Level.MODE.MEDIUM.toString();
        else
            return Common_Level.MODE.HARD.toString();


    }
}
package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dictionaryapp.Model.Word_Constructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Playing extends AppCompatActivity implements View.OnClickListener {
    final static long Interval=1000;
    final static long timeout=7000;
    CountDownTimer countDownTimer;
    ProgressBar progressBar;
    List<Word_Constructor> word_play= new ArrayList<>();
    DBmanager db;
    int index=0,score=0,thisQuestion=0,totalQuestion,correctQuestion,progressValue;
    TextView txtQuestion;
    Button btnA,btnB,btnC,btnD;
    String mode="";
    TextView txtScore;
    RelativeLayout container;
    AnimationDrawable anim;



    @Override
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();
        word_play=db.getAllWord_Mean(mode);
        totalQuestion=word_play.size();
        progressBar=findViewById(R.id.progressBar);

        countDownTimer= new CountDownTimer(timeout,Interval) {
            @Override
            public void onTick(long l) {
                progressBar.setProgress(progressValue);
                progressValue++;
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                showQuestion(++index);
            }
        };
        showQuestion(index);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        container = (RelativeLayout) findViewById(R.id.layoutPlaying);
        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(6000);
        anim.setExitFadeDuration(2000);
        Bundle extra=getIntent().getExtras();
        if(extra!=null)
        {
            mode=extra.getString("MODE");
        }
        db= new DBmanager(this);
        txtQuestion=findViewById(R.id.txtMeanQuestion);
        btnA=findViewById(R.id.btnA);
        btnB=findViewById(R.id.btnB);
        btnC=findViewById(R.id.btnC);
        btnD=findViewById(R.id.btnD);
        txtScore=findViewById(R.id.txtScore);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
    }
    private void showQuestion(int i)
    {
        Random rd= new Random();
        int correct=rd.nextInt(3);
        int randA,randB,randC,randD,randPlus;
        randPlus=4;
        randA = rd.nextInt(4);
        randB = rd.nextInt(4);
        randC = rd.nextInt(4);
        randD = rd.nextInt(4);
        while (randA==randB||randA==randC||randA==randD||randB==randC||randB==randD||randC==randD)
        {
            randA = rd.nextInt(4);
            randB = rd.nextInt(4);
            randC = rd.nextInt(4);
            randD = rd.nextInt(4);
        }





        if(index<totalQuestion)
        {
            progressValue=0;
            thisQuestion++;
            txtQuestion.setText(word_play.get(i).getMean().toLowerCase());
            if(correct==0) {
                btnA.setText(word_play.get(i).getWord());
                btnB.setText(word_play.get(randB+randPlus).getWord());
                while (btnA.getText().toString().equals(btnB.getText().toString())){
                    btnB.setText(word_play.get(randB+randPlus+1).getWord());
                }
                btnC.setText(word_play.get(randC+randPlus).getWord());
                btnD.setText(word_play.get(randD+randPlus).getWord());
            }
            else if(correct==1)
            {
                btnA.setText(word_play.get(randA+randPlus).getWord());
                btnB.setText(word_play.get(i).getWord());
                btnC.setText(word_play.get(randC+randPlus).getWord());
                btnD.setText(word_play.get(randD+randPlus).getWord());
            }
            else if(correct==2)
            {
                btnA.setText(word_play.get(randA+randPlus).getWord());
                btnB.setText(word_play.get(randB+randPlus).getWord());
                btnC.setText(word_play.get(i).getWord());
                btnD.setText(word_play.get(randD+randPlus).getWord());
            }
            else if(correct==3)
            {
                btnA.setText(word_play.get(randA+randPlus).getWord());
                btnB.setText(word_play.get(randB+randPlus).getWord());
                btnC.setText(word_play.get(randC+randPlus).getWord());
                btnD.setText(word_play.get(i).getWord());
            }


            countDownTimer.start();
        }
        else
        {
            Intent intent= new Intent(Playing.this,Score.class);
            Bundle bundle= new Bundle();
            bundle.putInt("Score",score);
           intent.putExtras(bundle);
           startActivity(intent);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }
    @Override
    public void onClick(View view) {
        countDownTimer.cancel();
        if(index<totalQuestion){
            Button clickerButton=(Button)view;
            if(clickerButton.getText().equals(word_play.get(index).getWord())) {
                score++;
                correctQuestion++;

                showQuestion(++index);

            }
            else
                showQuestion(++index);
            txtScore.setText(String.format("%d",score));
        }
    }
}
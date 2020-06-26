package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button Listdata_word,game_check_btn,exit_btn,find_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Listdata_word=findViewById(R.id.worddList_btn);
        game_check_btn=findViewById(R.id.btn_game_check);
        find_btn=findViewById(R.id.findBtn);
        exit_btn=findViewById(R.id.btn_exit);

        // event click
        find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,Search.class);
                startActivity(intent);
            }
        });
        Listdata_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,DataWord.class);
                startActivity(intent);
            }
        });
        game_check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,Game_Activity.class);
                startActivity(intent);
            }
        });
        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity= new MainActivity();
                finish();
            }
        });
    }

}
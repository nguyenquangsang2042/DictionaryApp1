package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DataWord extends AppCompatActivity {
  DBmanager dBmanager;
  ArrayList<String> listItem;
  ArrayAdapter adapter;
  ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_word);
        dBmanager=new DBmanager(this);
        listItem= new ArrayList<>();
        listView=findViewById(R.id.listViewWord);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text=listView.getItemAtPosition(i).toString();
                Toast.makeText(DataWord.this,""+text,Toast.LENGTH_SHORT).show();
            }
        });
    }





}
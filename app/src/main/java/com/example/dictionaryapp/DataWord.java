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

import com.example.dictionaryapp.Common.CustomAdapter;
import com.example.dictionaryapp.Model.Word_Constructor;

import java.util.ArrayList;
import java.util.List;

public class DataWord extends AppCompatActivity {
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_word);
        listView=(ListView)findViewById(R.id.listViewWord);
        DBmanager dBmanager= new DBmanager(this);
        List<Word_Constructor> list=dBmanager.getAllWord();
        if(list.size()>0)
        {
            CustomAdapter customAdapter=new CustomAdapter(this,list);
            listView.setAdapter(customAdapter);
        }


    }





}
package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends AppCompatActivity {

    String url;
    private EditText wordSearch;
    private TextView showDef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        showDef =findViewById(R.id.showDef);
        wordSearch=findViewById(R.id.Wsearch);
    }
    private String dictionaryEntries() {
        final String language = "en-gb";
        final String word = wordSearch.getText().toString();
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

    public void sendRequestOnclick(View v)
    {
        DictionaryRequest dR= new DictionaryRequest(this, showDef);
        url=dictionaryEntries();
        dR.execute(url);
    }
    public void addRecord(View view)
    {
        DBmanager db= new DBmanager(this);
        String res=db.addRecord(wordSearch.getText().toString(),showDef.getText().toString(),"0");
        Toast.makeText(this,res,Toast.LENGTH_LONG).show();
        wordSearch.setText("");
        showDef.setText("");

    }


}
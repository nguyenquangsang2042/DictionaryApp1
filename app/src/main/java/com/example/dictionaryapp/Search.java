package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends AppCompatActivity {

    String url;
    private EditText wordSearch;
    private TextView showDef;
    Button btnSavve;
    RelativeLayout container;
    AnimationDrawable anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        showDef =findViewById(R.id.showDef);
        wordSearch=findViewById(R.id.Wsearch);
        container = (RelativeLayout) findViewById(R.id.layoutSearch);

        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(6000);
        anim.setExitFadeDuration(2000);

        btnSavve=findViewById(R.id.btnSave);
        btnSavve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(showDef.getText().toString().equals("")||wordSearch.getText().toString().equals(""))
            {
                Toast.makeText(Search.this,"some thing wrong, can't save",Toast.LENGTH_SHORT).show();
            }
            else
            {

                addRecord(view);
                showDef.setText("");
                wordSearch.setText("");
            }
            }
        });
        wordSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showDef.setText("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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


    }
    @Override
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }


}
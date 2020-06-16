package com.example.dictionaryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmanager extends SQLiteOpenHelper {
    private static final String dbName="word_check_data.db";
    public DBmanager( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public String addRecord(String p1,String p2,String p3)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("word",p1);
        contentValues.put("mean",p2);
        contentValues.put("have_learned",p3);

        long res= db.insert("tblword",null,contentValues);
        if(res==-1)
        {
            return  "failed";
        }
        else
            return "success";
    }
}

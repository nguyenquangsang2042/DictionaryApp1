package com.example.dictionaryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmanager extends SQLiteOpenHelper{

    private static  final String dbName="Eng_Word.db";
    public DBmanager(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry_Create="create table tb_ListWord (id integer primary key autoincrement,word text,mean text,have_learn integer ) ";
        db.execSQL(qry_Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists tb_ListWord");
        onCreate(db);

    }
    public String addRecord(String p1,String p2,String p3)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("word",p1);
        cv.put("mean",p2);
        cv.put("have_learn",p3);

        long res=db.insert("tb_ListWord",null,cv);
        if(res==-1)
        {
            return "failed";
        }
        else
            return "success";

    }
    public Cursor viewData()
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String qrySelectToView="select * from tb_ListWord";
        Cursor cursor=sqLiteDatabase.rawQuery(qrySelectToView,null);

        return cursor;
    }
}

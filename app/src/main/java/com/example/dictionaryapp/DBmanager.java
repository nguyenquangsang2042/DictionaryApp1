package com.example.dictionaryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.dictionaryapp.Common.Common_Level;
import com.example.dictionaryapp.Model.Word_Constructor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBmanager extends SQLiteOpenHelper{

    private static  final String dbName="Eng_Word.db";
    private static String db_Path="";
    private SQLiteDatabase mDatabase;
    private Context mContext;
    public DBmanager(Context context) {

        super(context, dbName, null, 1);
        if(Build.VERSION.SDK_INT>=17)
            db_Path=context.getApplicationInfo().dataDir+"/database/";
        else
            db_Path=context.getApplicationInfo().dataDir+"/database/";
        Log.e("DBPATH:",db_Path);
        this.mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry_Create="create table tb_ListWord (id integer primary key autoincrement,word text,mean text,have_learn integer ) ";
        db.execSQL(qry_Create);
    }

    @Override
    public synchronized void close() {
        if(mDatabase!=null)
        {
            mDatabase.close();
        }
        super.close();
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
    public void openDB() {
        String myPath=db_Path+dbName;
        mDatabase=SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public void copyDB()throws IOException {
        try{
            InputStream myInput=mContext.getAssets().open(dbName);
            String outputFile=db_Path+dbName;
            OutputStream myOutput= new FileOutputStream(outputFile);
            byte[] buffer= new byte[1024];
            int length;
            while ((length=myInput.read(buffer))>0)
                myOutput.write(buffer,0,length);
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private boolean checkDB() {
        SQLiteDatabase tempDb=null;
        try{
            String myPath=db_Path+dbName;
            tempDb=SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        if(tempDb!=null)
        {
            tempDb.close();
        }
        return tempDb!=null?true:false;
    }
    public void createDB(){
        boolean isDBExists=checkDB();
        if(isDBExists)
        {

        }
        else
        {
            this.getReadableDatabase();
            try {
                copyDB();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    //get data
    public List<Word_Constructor> getAllWord() {
        List<Word_Constructor> listWord = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try {
            c = db.rawQuery("select * from tb_ListWord order by word", null);
            if (c == null) return null;
            c.moveToFirst();
            do {
                int id = c.getInt(c.getColumnIndex("id"));
                String word = c.getString(c.getColumnIndex("word"));
                String mean = c.getString(c.getColumnIndex("mean"));
                int have_learn = c.getInt(c.getColumnIndex("have_learn"));
                Word_Constructor word_constructor = new Word_Constructor(id, word, mean, have_learn);
                listWord.add(word_constructor);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listWord;
    }
    public List<Word_Constructor> getAllWord_Mean(String mode) {
        List<Word_Constructor> listWord = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        int limit=0;
        if(mode.equals(Common_Level.MODE.EASY.toString()))
            limit=10;
        else if(mode.equals(Common_Level.MODE.MEDIUM.toString()))
            limit=20;
        else if(mode.equals(Common_Level.MODE.HARD.toString()))
            limit=30;
        try {
            c = db.rawQuery(String.format("select * from tb_ListWord order by Random() limit %d",limit), null);
            if (c == null) return null;
            c.moveToFirst();
            do {
                int id = c.getInt(c.getColumnIndex("id"));
                String word = c.getString(c.getColumnIndex("word"));
                String mean = c.getString(c.getColumnIndex("mean"));
                int have_learn = c.getInt(c.getColumnIndex("have_learn"));
                Word_Constructor word_constructor = new Word_Constructor(id, word, mean, have_learn);
                listWord.add(word_constructor);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listWord;
    }

}

package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="Login.db";

    private static final String TABLE_NAME="users";
    private static final String COLUMN_USER="username";
    private static final String COLUMN_PASSWORD="password";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE " + TABLE_NAME +" ("+COLUMN_USER+" TEXT primary Key, "+COLUMN_PASSWORD+
                " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists "+TABLE_NAME);
        onCreate(db);
    }
    public Boolean insertData(String username, String passwords) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password", passwords);
        long result = db.insert("users",null,contentValues);

        if(result ==-1)
            return  false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME+" where username = ?", new String[]{username});
        if (cursor.getCount()>0)
            return  true;
        else
            return  false;
    }

    public Boolean checkusernamepassword(String username, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("Select * from "+TABLE_NAME+" where username = ? and password = ?",new String[] {username,password});
        if(cursor.getCount()>0)
            return  true;
        else
            return false;
    }
}

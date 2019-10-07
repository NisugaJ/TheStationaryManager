package com.example.stationerymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class callDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CallInfo.db";

    public callDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES = ("CREATE TABLE "+ callMaster.call.TABLE_NAME + " (" +
                callMaster.call._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                callMaster.call.COLUMN_NAME_NAME + " TEXT," +
                callMaster.call.COLUMN_NAME_NUMBER + " INTEGER," +
                callMaster.call.COLUMN_NAME_HOWMUCH + " FLOAT," +
                callMaster.call.COLUMN_NAME_NOOFMINS + " INTEGER)");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + callMaster.call.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String no,String howMuch,String mins){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(callMaster.call.COLUMN_NAME_NAME,name);
        contentValues.put(callMaster.call.COLUMN_NAME_NUMBER,no);
        contentValues.put(callMaster.call.COLUMN_NAME_HOWMUCH,howMuch);
        contentValues.put(callMaster.call.COLUMN_NAME_NOOFMINS,mins);

        Long result = db.insert(callMaster.call.TABLE_NAME,null,contentValues);

        if(result == -1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + callMaster.call.TABLE_NAME ,null );

        return res;
    }
    public boolean updateData(String id,String name,String no,String howMuch,String mins){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(callMaster.call._ID,id);
        contentValues.put(callMaster.call.COLUMN_NAME_NAME,name);
        contentValues.put(callMaster.call.COLUMN_NAME_NUMBER,no);
        contentValues.put(callMaster.call.COLUMN_NAME_HOWMUCH,howMuch);
        contentValues.put(callMaster.call.COLUMN_NAME_NOOFMINS,mins);

        db.update(callMaster.call.TABLE_NAME,contentValues,callMaster.call._ID + " = ?",new String[]{id});
        return true;
    }

    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(callMaster.call.TABLE_NAME,callMaster.call._ID + " = ?",new String[]{id});
    }
}

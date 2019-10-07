package com.example.stationerymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class faxDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FaxInfo.db";

    public faxDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES = ("CREATE TABLE "+ faxMaster.fax.TABLE_NAME + " (" +
                faxMaster.fax._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                faxMaster.fax.COLUMN_NAME_NAME + " TEXT," +
                faxMaster.fax.COLUMN_NAME_NUMBER + " INTEGER," +
                faxMaster.fax.COLUMN_NAME_HOWMUCH + " FLOAT," +
                faxMaster.fax.COLUMN_NAME_NOOFFAX + " INTEGER)");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + faxMaster.fax.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String no,String howMuch,String noFax){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(faxMaster.fax.COLUMN_NAME_NAME,name);
        contentValues.put(faxMaster.fax.COLUMN_NAME_NUMBER,no);
        contentValues.put(faxMaster.fax.COLUMN_NAME_HOWMUCH,howMuch);
        contentValues.put(faxMaster.fax.COLUMN_NAME_NOOFFAX,noFax);

        Long result = db.insert(faxMaster.fax.TABLE_NAME,null,contentValues);

        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + faxMaster.fax.TABLE_NAME ,null );

        return res;
    }

    public boolean updateData(String id,String name,String no,String howMuch,String noFax){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(faxMaster.fax._ID,id);
        contentValues.put(faxMaster.fax.COLUMN_NAME_NAME,name);
        contentValues.put(faxMaster.fax.COLUMN_NAME_NUMBER,no);
        contentValues.put(faxMaster.fax.COLUMN_NAME_HOWMUCH,howMuch);
        contentValues.put(faxMaster.fax.COLUMN_NAME_NOOFFAX,noFax);

        db.update(faxMaster.fax.TABLE_NAME,contentValues,faxMaster.fax._ID + " = ?",new String[]{id});
        return true;
    }

    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(faxMaster.fax.TABLE_NAME,faxMaster.fax._ID + " = ?",new String[]{id});
    }
}

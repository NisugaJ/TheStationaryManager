package com.example.stationerymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class StationarySalesDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Stationery.db";
    public static final String TABLE_NAME = "stationerySalesTable";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "CODE";
    public static final String COL_4 = "NAME";
    public static final String COL_5 = "PRICE";
    public static final String COL_6 = "QUANTITY";




    public StationarySalesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, CODE TEXT, NAME TEXT, PRICE TEXT, QUANTITY TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME );
        onCreate(db);

    }

    public boolean insertData(String DATE, String CODE, String NAME, String PRICE, String QUANTITY){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, DATE);
        contentValues.put(COL_3, CODE);
        contentValues.put(COL_4, NAME);
        contentValues.put(COL_5, PRICE);
        contentValues.put(COL_6, QUANTITY);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }

    }


    public void updateData(String DATE, String CODE, String NAME, String PRICE, String QUANTITY, String ID){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "UPDATE " + TABLE_NAME + " SET DATE=?, CODE=?, NAME=?, PRICE=?, QUANTITY=? WHERE ID=?";

        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindString(1,DATE);
        statement.bindString(2,CODE);
        statement.bindString(3,NAME);
        statement.bindString(4,PRICE);
        statement.bindString(5,QUANTITY);
        statement.bindString(6,ID);

        statement.execute();
        db.close();
    }


    public void deleteData(String ID){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID=?";

        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,ID);

        statement.execute();
        db.close();
    }


    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
        return res;

    }

    public Cursor getData(String sql){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(sql, null);
    }
}

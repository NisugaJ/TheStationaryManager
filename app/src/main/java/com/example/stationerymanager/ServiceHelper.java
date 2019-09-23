package com.example.stationerymanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.Locale;

public class ServiceHelper extends SQLiteOpenHelper {

    public ServiceHelper( Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

   /* public void queryDataNew(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }*/

    public void insertData(String Date,String ServiceName, String Category, String price, String Quantity, String profit){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "INSERT INTO RECORD VALUES(NULL, ?, ?, ?, ?, ?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,Date);
        statement.bindString(2,ServiceName);
        statement.bindString(3,Category);
        statement.bindString(4,price);
        statement.bindString(5,Quantity);
        statement.bindString(6,profit);

        statement.executeInsert();
    }



    public boolean updateData(String Date,String ServiceName,String Category,String price,String Quantity,String profit) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE RECORD SET Date=?, ServiceName=?, Category=?, price=?, Quantity=?, profit=? WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, Date);
        statement.bindString(2, ServiceName);
        statement.bindString(3, Category);
        statement.bindString(4, price);
        statement.bindString(5, Quantity);
        statement.bindString(6, profit);
        // statement.bindDouble(7,(double)id);

        statement.execute();
        return true;
    }

    public void deleteData(int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM RECORD WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}

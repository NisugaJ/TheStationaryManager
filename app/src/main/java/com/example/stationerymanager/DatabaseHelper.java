package com.example.stationerymanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper( Context context, String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryDataNew(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertDataNew(String ServiceName,String ServiceType,String Description,String CostPrice,String SellingPrice,String Quantity ){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "INSERT INTO RECORDNew VALUES(NULL,?,?,?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,ServiceName);
        statement.bindString(2,ServiceType);
        statement.bindString(3,Description);
        statement.bindString(4,CostPrice);
        statement.bindString(5,SellingPrice);
        statement.bindString(6,Quantity);

        statement.executeInsert();
    }

    public void updateDataNew(String ServiceName,String ServiceType,String Description,String CostPrice,String SellingPrice,String Quantity,int ID){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE RECORDNew SET ServiceName = ?, ServiceType=?, Description=?, CostPrice=?, SellingPrice=?, Quantity=? WHERE ID=?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1,ServiceName);
        statement.bindString(2,ServiceType);
        statement.bindString(3,Description);
        statement.bindString(4,CostPrice);
        statement.bindString(5,SellingPrice);
        statement.bindString(6,Quantity);
        statement.bindDouble(7,(double)ID);

        statement.execute();
        database.close();
    }

    public void deleteDataNew(int ID){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM RECORDNew WHERE ID=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double)ID);

        statement.execute();
        database.close();
    }

    public Cursor getDataNew(String sql){
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

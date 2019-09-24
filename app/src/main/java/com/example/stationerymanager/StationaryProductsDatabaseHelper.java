package com.example.stationerymanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class StationaryProductsDatabaseHelper extends SQLiteOpenHelper {

    public StationaryProductsDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void queryDataProduct(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }


    //insert data
    public void insertDataProduct(String category, String code, String name, String cPrice, String sPrice, String quantity){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "INSERT INTO stationeryProductsTable VALUES(NULL, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, category);
        statement.bindString(2, code);
        statement.bindString(3, name);
        statement.bindString(4, cPrice);
        statement.bindString(5, sPrice);
        statement.bindString(6, quantity);

        statement.executeInsert();

    }


    //update data
    public boolean updateDataProduct(String category, String code, String name, String cPrice, String sPrice, String quantity, int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE stationeryProductsTable SET category = ?, code = ?, name = ?, cPrice = ?, sPrice = ?, quantity = ? WHERE id = ?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, category);
        statement.bindString(2, code);
        statement.bindString(3, name);
        statement.bindString(4, cPrice);
        statement.bindString(5, sPrice);
        statement.bindString(6, quantity);
        statement.bindDouble(7, (double)id);

        statement.execute();
        database.close();
        return true;
    }


    //delete data
    public void deleteDataProduct(int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM stationeryProductsTable WHERE id = ?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }


    public Cursor getDataProduct(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

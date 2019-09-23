package com.example.stationerymanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteExpensesHelper extends SQLiteOpenHelper {

    SQLiteExpensesHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);

    }

    public void creatExpensesTable(String queryString){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(queryString);
    }

    public void insertExpense(String title, String type, String amount , String timestamp, String noteDescription ){
        SQLiteDatabase database = getWritableDatabase();
        String insertSQL = "INSERT INTO expenses VALUES (NULL, ?,?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(insertSQL);
        statement.clearBindings();

        statement.bindString(1, title);
        statement.bindString(2, type);
        statement.bindString(3, amount);
        statement.bindString(4, timestamp);
        statement.bindString(5, noteDescription);

        statement.executeInsert();
        database.close();
    }

    public void updateExpense(int id, String title, String type, String amount , String timestamp, String noteDescription  ){
        SQLiteDatabase database = getWritableDatabase();
        String updateSQL = "UPDATE expenses SET title = ?," +
                                                "type = ?," +
                                                "amount = ?," +
                                                "dateANDtime = ?," +
                                                "note = ? " +
                                                "WHERE expenseID = ?";

        SQLiteStatement statement = database.compileStatement(updateSQL);
        statement.bindString(1, title);
        statement.bindString(2, type);
        statement.bindString(3, amount);
        statement.bindString(4, timestamp);
        statement.bindString(5, noteDescription);
        statement.bindDouble(6, id);
        statement.execute();
        database.close();

    }

    public  void deleteExpense(int id){
        SQLiteDatabase database = getWritableDatabase();
        String deleteSQL = "DELETE FROM expenses WHERE expenseID = ?";

        SQLiteStatement statement = database.compileStatement(deleteSQL);
        statement.clearBindings();

        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getExpenses(String sql){
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

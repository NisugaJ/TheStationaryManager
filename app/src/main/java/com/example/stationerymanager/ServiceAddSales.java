package com.example.stationerymanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.DriverManager;
import java.util.ArrayList;

public class ServiceAddSales extends AppCompatActivity {

    ListView nListView;
    ArrayList<Model> nList;
    ServiceAdapter nAdapter = null;
    public static ServiceHelper nDataBaseHelper;
    ArrayAdapter SpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add_sales);
        setTitle("Sales");


        nListView = findViewById(R.id.listView);
        nList = new ArrayList<>();
        nAdapter = new ServiceAdapter(this, R.layout.row, nList);
        nListView.setAdapter(nAdapter);
        nDataBaseHelper = new ServiceHelper(this, "STATIONERYDB.sqlite", null, 2);

        try {
            Cursor cursor = nDataBaseHelper.getData("SELECT * FROM RECORD");
            nList.clear();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
               String Date = cursor.getString(1);
                String ServiceName = cursor.getString(2);
                String Category = cursor.getString(3);
                String price = cursor.getString(4);
                String Quantity = cursor.getString(5);
                String profit = cursor.getString(6);

                nList.add(new Model(id,Date,ServiceName,Category, price, Quantity, profit));
            }
        }catch (SQLiteException e){
            Log.e("DB error", e.toString());
        }


        nAdapter.notifyDataSetChanged();
        if (nList.size() == 0) {
            Toast.makeText(this,"No Record Found....",Toast.LENGTH_SHORT).show();
        }

   }


    public void addSales(View view){
        Intent intent = new Intent(getApplicationContext(), AddServiceSales.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        nList.clear();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}

package com.example.stationerymanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.stationerymanager.AddSales.myDb;

public class SalesListActivity extends AppCompatActivity {

    ListView mListView;
    SalesListAdapter mAdapter = null;
    ArrayList<sModel> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_list);


        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new SalesListAdapter(this, R.layout.salesrow, mList);
        mListView.setAdapter(mAdapter);

        Cursor res = AddSales.myDb.getAllData();
        mList.clear();

        while (res.moveToNext()) {
            String ID = res.getString(0);
            String DATE = res.getString(1);
            String CODE = res.getString(2);
            String NAME = res.getString(3);
            String PRICE = res.getString(4);
            String QUANTITY = res.getString(5);

            mList.add(new sModel(ID, DATE, CODE, NAME, PRICE, QUANTITY));
        }


        mAdapter.notifyDataSetChanged();

        if (mList.size() == 0) {
            Toast.makeText(this, "No DATA Found...", Toast.LENGTH_SHORT).show();
        }


    }

}

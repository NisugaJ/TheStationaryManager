package com.example.stationerymanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    ListView pListView;
    ArrayList<pModel> pList;
    ProductsListAdapter pAdapter = null;
    ArrayAdapter SpinnerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Intent intent = getIntent();
        String currentCategory = intent.getStringExtra("product_type");
        setTitle(currentCategory + " List");

        pListView = findViewById(R.id.pListView);
        pList = new ArrayList<>();
        pAdapter = new ProductsListAdapter(this, R.layout.productsrow, pList);
        pListView.setAdapter(pAdapter);

        try{
            Cursor cursor = AddProducts.spDb.getDataProduct("SELECT * FROM stationeryProductsTable WHERE category = '"+currentCategory+"';");
            pList.clear();

            //get all data from sqlite

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String category = cursor.getString(1);
                String code = cursor.getString(2);
                String name = cursor.getString(3);
                String cPrice = cursor.getString(4);
                String sPrice = cursor.getString(5);
                String quantity = cursor.getString(6);

                //add to list
                pList.add(new pModel(id, category, code, name, cPrice, sPrice, quantity));
            }
        }catch (SQLException e){
            Log.e("DB_error", e.toString());
        }

        pAdapter.notifyDataSetChanged();

        if (pList.size() == 0) {
            Toast.makeText(this, "No Products Found...", Toast.LENGTH_SHORT).show();
        }

    }

}

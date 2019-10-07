package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProducts extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText pCode, pName, pCostPrice, pSelPrice, pQty;
    Spinner category;
    Button addProduct, showList;

    public static StationaryProductsDatabaseHelper spDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        getSupportActionBar().setTitle("Add Products");

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.combo_box1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        category = findViewById(R.id.spinner1);
        pCode = findViewById(R.id.PAfill2);
        pName = findViewById(R.id.PAfill1);
        pCostPrice = findViewById(R.id.PAfill3);
        pSelPrice = findViewById(R.id.PAfill4);
        pQty = findViewById(R.id.PAfill5);
        addProduct = findViewById(R.id.PAbtn1);
        //showList = findViewById(R.id.PAbtn);


        //creating database
        spDb = new StationaryProductsDatabaseHelper(this, "Stationery.db", null, 1);

        //creating table
        spDb.queryDataProduct("CREATE TABLE IF NOT EXISTS stationeryProductsTable(id INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR, code VARCHAR, name VARCHAR, cPrice VARCHAR, sPrice VARCHAR, quantity VARCHAR)");



        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pCode.length() != 0 && pName.length() != 0 && pCostPrice.length() != 0 && pSelPrice.length() != 0 && pQty.length() != 0){
                    try{
                        spDb.insertDataProduct(
                                category.getSelectedItem().toString().trim(),
                                pCode.getText().toString().trim(),
                                pName.getText().toString().trim(),
                                pCostPrice.getText().toString().trim(),
                                pSelPrice.getText().toString().trim(),
                                pQty.getText().toString().trim()

                        );

                        Toast.makeText(AddProducts.this, "Data Inserted ", Toast.LENGTH_LONG).show();

                        category.setSelection(0);
                        pCode.setText("");
                        pName.setText("");
                        pCostPrice.setText("");
                        pSelPrice.setText("");
                        pQty.setText("");

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }

                else {
                    Toast.makeText(AddProducts.this, "Please fill out the empty fields", Toast.LENGTH_LONG).show();
                }



            }
        });

//        showList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AddProducts.this, ProductListActivity.class));
//
//            }
//        });
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String.text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

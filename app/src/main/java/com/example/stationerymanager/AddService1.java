package com.example.stationerymanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddService1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{



    EditText nServiceType,nDescription,nCostPrice,nSellingPrice,nQuantity;
    Button nbtnSerAdd;
    Spinner nServiceName;

    public static DatabaseHelper mDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service1);
        setTitle("Add Services");

      // ActionBar actionBar = getSupportActionBar();
       // actionBar.setTitle("New Record");



     Spinner spinner = findViewById(R.id.spinneradd1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.comboboxadd1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        nServiceName = (Spinner) findViewById(R.id.spinneradd1);
       nServiceType = (EditText)findViewById(R.id.filladd1);
        nDescription = (EditText)findViewById(R.id.filladd2);
        nCostPrice =  (EditText)findViewById(R.id.filladd3);
        nSellingPrice = (EditText)findViewById(R.id.filladd);
        nQuantity = (EditText)findViewById(R.id.filladd5);
        nbtnSerAdd = (Button)findViewById(R.id.btnadd1);
       // mbtnList = (Button)findViewById(R.id.btnSList);

        mDataBaseHelper = new DatabaseHelper(this, "STATIONERYDB.sqlite", null, 2);

        //creating table in database

       mDataBaseHelper.queryDataNew("CREATE TABLE IF NOT EXISTS RECORDNew(ID INTEGER PRIMARY KEY AUTOINCREMENT,ServiceName VARCHAR,ServiceType VARCHAR,Description VARCHAR,CostPrice VARCHAR,SellingPrice VARCHAR,Quantity VARCHAR)");
         //mDataBaseHelper.queryDataNew("DROP TABLE RECORDNew");

        nbtnSerAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nServiceType.length() != 0 && nDescription.length() != 0 && nCostPrice.length() != 0 && nSellingPrice.length() != 0 && nQuantity.length() != 0) {

                    String NumberPattern = "[1-9][0-9]*|0";

                    if (nCostPrice.getText().toString().matches(NumberPattern) && nSellingPrice.getText().toString().matches(NumberPattern) && nQuantity.getText().toString().matches(NumberPattern)) {
                        Toast.makeText(getApplicationContext(), "values are valid", Toast.LENGTH_SHORT).show();

                try {
                    System.out.println(nServiceName.getSelectedItem().toString().trim());





                        mDataBaseHelper.insertDataNew(
                                nServiceName.getSelectedItem().toString().trim(),
                                nServiceType.getText().toString().trim(),
                                nDescription.getText().toString().trim(),
                                nCostPrice.getText().toString().trim(),
                                nSellingPrice.getText().toString().trim(),
                                nQuantity.getText().toString().trim());

                        Toast.makeText(AddService1.this, "Data Inserted ", Toast.LENGTH_LONG).show();

                        nServiceName.setSelection(0);
                        nServiceType.setText("");
                        nDescription.setText("");
                        nCostPrice.setText("");
                        nSellingPrice.setText("");
                        nQuantity.setText("");
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }else{

                        Toast.makeText(AddService1.this, "Please fill with valid numbers", Toast.LENGTH_LONG).show();

                    }



                }else{
                    Toast.makeText(AddService1.this, "Please fill out the empty fields", Toast.LENGTH_LONG).show();

                }
            }
        });

       /* mbtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddService1.this, BindingDisplay.class));
            }
        });*/
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


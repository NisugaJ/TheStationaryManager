package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddProducts extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText pCode, pName, pCostPrice, pSelPrice, pQty;
    //Spinner category;
    Button addProduct, showList;

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

        pCode = findViewById(R.id.PAfill2);
        pName = findViewById(R.id.PAfill1);
        pCostPrice = findViewById(R.id.PAfill3);
        pSelPrice = findViewById(R.id.PAfill4);
        pQty = findViewById(R.id.PAfill5);
        addProduct = findViewById(R.id.PAbtn1);
        showList = findViewById(R.id.PAbtn);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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

package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateProducts extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText uCode, uName, uCPrice, uSPrice, uQty;
    Button update;
    Spinner uCategory;

    public static StationaryProductsDatabaseHelper spDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);

        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.combo_box1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        uCategory = findViewById(R.id.spinner2);
        uCode = findViewById(R.id.ufill2);
        uName = findViewById(R.id.ufill1);
        uCPrice = findViewById(R.id.ufill3);
        uSPrice = findViewById(R.id.ufill4);
        uQty = findViewById(R.id.ufill5);
        update = findViewById(R.id.UPbtn1);

        Intent intent = getIntent();
        final Integer id = Integer.valueOf(intent.getIntExtra("id", -999));
        uCategory.setSelection(adapter.getPosition(intent.getStringExtra("category")));
        uCode.setText(intent.getStringExtra("code"));
        uName.setText(intent.getStringExtra("name"));
        uCPrice.setText(intent.getStringExtra("cPrice"));
        uSPrice.setText(intent.getStringExtra("sPrice"));
        uQty.setText(intent.getStringExtra("quantity"));


        spDb = new StationaryProductsDatabaseHelper(this, "Stationery.db", null, 1);
        spDb.queryDataProduct("CREATE TABLE IF NOT EXISTS stationeryProductsTable(id INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR, code VARCHAR, name VARCHAR, cPrice VARCHAR, sPrice VARCHAR, quantity VARCHAR)");



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isPUpdated = spDb.updateDataProduct(
                        uCategory.getSelectedItem().toString().trim(),
                        uCode.getText().toString().trim(),
                        uName.getText().toString().trim(),
                        uCPrice.getText().toString().trim(),
                        uSPrice.getText().toString().trim(),
                        uQty.getText().toString().trim(),
                        id
                );

                if (isPUpdated) {
                    Toast.makeText(getApplicationContext(), "Data Updated!", Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(UpdateProducts.this, "Data Not Updated!", Toast.LENGTH_LONG).show();
                }


            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

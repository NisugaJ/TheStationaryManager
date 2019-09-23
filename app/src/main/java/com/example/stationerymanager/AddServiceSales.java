package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;


import android.os.Bundle;

public class AddServiceSales extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    EditText  editCategory, editPrice, editQuantity, editProfit;
    Button btnAddData, nBtnList;
    TextView editDate;
    Spinner editServiceName;

    public static ServiceHelper nDataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_sales);
        setTitle("Add Sales");

        Spinner spinner = findViewById(R.id.spinneradd);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.comboboxadd1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        editDate = (TextView) findViewById(R.id.textView7);
        editServiceName = (Spinner) findViewById(R.id.spinneradd);
        editCategory = (EditText) findViewById(R.id.fillsale3);
        editPrice = (EditText) findViewById(R.id.fillsale4);
        editQuantity = (EditText) findViewById(R.id.fillsale5);
        editProfit = (EditText) findViewById(R.id.fillsale);
        btnAddData = (Button) findViewById(R.id.btnsale1);
        nBtnList = (Button) findViewById(R.id.btnList);

        nDataBaseHelper = new ServiceHelper(this, "STATIONERYDB.sqlite", null, 2);

        nDataBaseHelper.queryData("CREATE TABLE IF NOT EXISTS RECORD(id INTEGER PRIMARY KEY AUTOINCREMENT,Date TEXT,ServiceName VARCHAR,Category VARCHAR,price VARCHAR,Quantity VARCHAR,profit VARCHAR)");
        // nDataBaseHelper.queryData("DROP TABLE RECORD");

        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerSerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });


        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editDate.length() != 0 && editCategory.length() != 0 && editPrice.length() != 0 && editQuantity.length() != 0 && editProfit.length() != 0) {

                    String NumberPattern = "[1-9][0-9]*|0";

                    if (editPrice.getText().toString().matches(NumberPattern) && editQuantity.getText().toString().matches(NumberPattern) && editProfit.getText().toString().matches(NumberPattern)) {
                        Toast.makeText(getApplicationContext(), "values are valid", Toast.LENGTH_SHORT).show();


                        try {
                            nDataBaseHelper.insertData(
                                    editDate.getText().toString().trim(),
                                    editServiceName.getSelectedItem().toString().trim(),
                                    editCategory.getText().toString().trim(),
                                    editPrice.getText().toString().trim(),
                                    editQuantity.getText().toString().trim(),
                                    editProfit.getText().toString().trim());

                            Toast.makeText(AddServiceSales.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            editDate.setText("");
                            editServiceName.setSelection(0);
                            editCategory.setText("");
                            editPrice.setText("");
                            editQuantity.setText("");
                            editProfit.setText("");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                } else {

                    Toast.makeText(AddServiceSales.this, "Please fill with valid numbers", Toast.LENGTH_LONG).show();
                }}
            else
                {
                    Toast.makeText(AddServiceSales.this, "Please fill out the empty fields", Toast.LENGTH_LONG).show();

                }
            }

        });



        nBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddServiceSales.this, ServiceAddSales.class));
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.textView7);
        textView.setText(currentDateString);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


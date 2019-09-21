package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;


import android.os.Bundle;

public class AddServiceSales extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText editServiceName, editCategory, editPrice, editQuantity, editProfit;
    Button btnAddData, nBtnList;
   TextView editDate;

    public static ServiceHelper nDataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_sales);
        setTitle("Add Sales");

        editDate = (TextView) findViewById(R.id.textView7);
        editServiceName = (EditText) findViewById(R.id.fillsale2);
        editCategory = (EditText) findViewById(R.id.fillsale3);
        editPrice = (EditText) findViewById(R.id.fillsale4);
        editQuantity = (EditText) findViewById(R.id.fillsale5);
        editProfit = (EditText) findViewById(R.id.fillsale);
        btnAddData = (Button) findViewById(R.id.btnsale1);
        nBtnList = (Button) findViewById(R.id.btnList);

        nDataBaseHelper = new ServiceHelper(this, "STATIONERYDB.sqlite", null, 2);

       nDataBaseHelper.queryData("CREATE TABLE IF NOT EXISTS RECORD(id INTEGER PRIMARY KEY AUTOINCREMENT,Date TEXT,ServiceName VARCHAR,Category VARCHAR,price VARCHAR,Quantity VARCHAR,profit VARCHAR)");
       // nDataBaseHelper.queryData("DROP TABLE RECORD");

        Button button =(Button) findViewById(R.id.button3);
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
                try {
                    nDataBaseHelper.insertData(
                            editDate.getText().toString().trim(),
                            editServiceName.getText().toString().trim(),
                            editCategory.getText().toString().trim(),
                            editPrice.getText().toString().trim(),
                            editQuantity.getText().toString().trim(),
                            editProfit.getText().toString().trim());

                    Toast.makeText(AddServiceSales.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    editDate.setText("");
                    editServiceName.setText("");
                    editCategory.setText("");
                    editPrice.setText("");
                    editQuantity.setText("");
                    editProfit.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
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
    }


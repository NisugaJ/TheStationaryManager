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
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceSaleUpdate1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText  SeditCategory, SeditPrice, SeditQuantity, SeditProfit;
    Button Salebtnup1;
    TextView SeditDate;
    Spinner SeditServiceName;


    public static ServiceHelper nDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_sale_update1);
        setTitle("Sale Update");
        Salebtnup1=(Button)findViewById(R.id.btnup1);



        Spinner spinner = findViewById(R.id.Sspinneradd);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.comboboxadd1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        SeditDate = (TextView) findViewById(R.id.StextView7);
        SeditServiceName = (Spinner) findViewById(R.id.Sspinneradd);
        SeditCategory = (EditText) findViewById(R.id.Sfillsale3);
        SeditPrice = (EditText) findViewById(R.id.Sfillsale4);
        SeditQuantity = (EditText) findViewById(R.id.Sfillsale5);
        SeditProfit = (EditText) findViewById(R.id.Sfillsale);
        Salebtnup1 = (Button) findViewById(R.id.Sbtnup1);

        Intent intent = getIntent();
        final Integer  saleID = Integer.valueOf(intent.getIntExtra("id", -999));
        SeditDate.setText(intent.getStringExtra("Date"));
        SeditServiceName.setSelection( adapter.getPosition(intent.getStringExtra("ServiceName")) );
        SeditCategory.setText(intent.getStringExtra("category"));
        SeditPrice.setText(intent.getStringExtra("Price"));
        SeditQuantity.setText(intent.getStringExtra("Quantity"));
        SeditProfit.setText(intent.getStringExtra("Profit"));


        nDataBaseHelper = new ServiceHelper(this, "STATIONERYDB.sqlite", null, 2);

        nDataBaseHelper.queryData("CREATE TABLE IF NOT EXISTS RECORD(id INTEGER PRIMARY KEY AUTOINCREMENT,Date TEXT,ServiceName VARCHAR,Category VARCHAR,price VARCHAR,Quantity VARCHAR,profit VARCHAR)");
        // nDataBaseHelper.queryData("DROP TABLE RECORD");

        Salebtnup1.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        //int selectedID = recint.getIntExtra("ID",0);

                        boolean isUpdated;
                        isUpdated = nDataBaseHelper.updateData(SeditDate.getText().toString().trim(), SeditServiceName.getSelectedItem().toString().trim(), SeditCategory.getText().toString().trim(), SeditPrice.getText().toString().trim(), SeditQuantity.getText().toString().trim(), SeditProfit.getText().toString().trim(), saleID );

                        if (SeditDate.length() != 0 && SeditCategory.length() != 0 && SeditPrice.length() != 0 && SeditQuantity.length() != 0 && SeditProfit.length() != 0) {

                            if (isUpdated) {
                                Toast.makeText(getApplicationContext(), "Data Updated!", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(ServiceSaleUpdate1.this, "Data Not Updated!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(ServiceSaleUpdate1.this, "Please enter all details!", Toast.LENGTH_LONG).show();

                        }

                    }
                }
        );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


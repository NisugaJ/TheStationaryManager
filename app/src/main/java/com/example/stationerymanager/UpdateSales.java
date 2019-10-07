package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.os.Build.ID;

public class UpdateSales extends AppCompatActivity {

    EditText sDate, sCode, sName, sPrice, sQty;
    Button updateSale;

    public static StationarySalesDatabaseHelper ssDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sales2);

        sDate = findViewById(R.id.editText30);
        sCode = findViewById(R.id.SAfill10);
        sName = findViewById(R.id.SAfill20);
        sPrice = findViewById(R.id.SAfill30);
        sQty = findViewById(R.id.SAfill40);
        updateSale = findViewById(R.id.updt);

        Intent intent = getIntent();
        sDate.setText(intent.getStringExtra("DATE"));
        sCode.setText(intent.getStringExtra("CODE"));
        sName.setText(intent.getStringExtra("NAME"));
        sPrice.setText(intent.getStringExtra("PRICE"));
        sQty.setText(intent.getStringExtra("QUANTITY"));

        ssDb = new StationarySalesDatabaseHelper(this, "Stationery.db", null, 1);
        ssDb.queryDataSales("CREATE TABLE IF NOT EXISTS stationerySalesTable(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, CODE TEXT, NAME TEXT, PRICE TEXT, QUANTITY TEXT)");


        updateSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSUpdated = ssDb.updateData(
                        sDate.getText().toString().trim(),
                        sCode.getText().toString().trim(),
                        sName.getText().toString().trim(),
                        sPrice.getText().toString().trim(),
                        sQty.getText().toString().trim(),
                        ID
                );

                if (isSUpdated) {
                    Toast.makeText(getApplicationContext(), "Data Updated!", Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(UpdateSales.this, "Data Not Updated!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

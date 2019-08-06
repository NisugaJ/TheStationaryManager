package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ServiceAddSales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add_sales);
        setTitle("Sales");
    }

    public void addSales(View view){
        Intent intent = new Intent(getApplicationContext(), AddServiceSales.class);
        startActivity(intent);
    }
}

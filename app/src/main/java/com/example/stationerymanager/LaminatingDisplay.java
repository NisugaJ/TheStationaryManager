package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LaminatingDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laminating_display);
        setTitle("Service List");
    }

    public void editLaminatings(View view){
        Intent intent =  new Intent(getApplicationContext(),ServiceUpdate1.class);
        startActivity(intent);
    }
}

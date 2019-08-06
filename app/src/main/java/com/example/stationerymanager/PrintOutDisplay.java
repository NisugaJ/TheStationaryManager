package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrintOutDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_out_display);
        setTitle("Service List");
    }
    public void print(View v){
        Intent i = new Intent(PrintOutDisplay.this,ServiceUpdate1.class);
        startActivity(i);
    }
}

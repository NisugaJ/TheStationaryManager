package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BindingDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_display);
        setTitle("Service List");
    }

    public void editBinding(View view){
        Intent intent =  new Intent(BindingDisplay.this,ServiceUpdate1.class);
        startActivity(intent);
    }
}

package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PhotoCopyDisplay extends AppCompatActivity {

    ImageButton b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_copy_display);
        setTitle("Service List");
        b1=findViewById(R.id.imageButton6);
    }


    public void copy(View v){
        Intent i = new Intent(PhotoCopyDisplay.this,ServiceUpdate1.class);
        startActivity(i);
    }
}

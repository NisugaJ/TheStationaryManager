package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimePickActivity extends AppCompatActivity {
    TimePicker timepicker;
    Button changetime;
    Button cancelBtn;

    public  static  final String TIME_PASS_KEY = "newTime";
    public  static  final String TIME_PASS_KEY_PREFERENCE = "newTime_PREF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_pick);

        cancelBtn = findViewById(R.id.cancelBtn);
        timepicker= findViewById(R.id.timePickerWidget);
        //Uncomment the below line of code for 24 hour view
        timepicker.setIs24HourView(true);
        changetime= findViewById(R.id.btnSetTime);

    }

    public String getCurrentTime(){
        String currentTime= timepicker.getCurrentHour()+":"+timepicker.getCurrentMinute();
        return currentTime;
    }

    @Override
    protected void onResume() {
        super.onResume();

        changetime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String data = getCurrentTime();
        SharedPreferences sp = getSharedPreferences(TIME_PASS_KEY_PREFERENCE, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TIME_PASS_KEY,data);
        editor.apply();
    }
}

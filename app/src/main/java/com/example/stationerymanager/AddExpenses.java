package com.example.stationerymanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.TimeUnit;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.time.Clock;
import java.util.Calendar;

public class AddExpenses extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_Date;
    private ImageView imageCalender;
    private EditText editText_Time;
    private ImageView imageTime;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        setTitle(R.string.add_expense);

        imageCalender = findViewById(R.id.imageCalender);
        editText_Date = findViewById(R.id.editText_Date);
        imageTime = findViewById(R.id.imageTime);
        editText_Time = findViewById(R.id.editText_Time);

        imageCalender.setOnClickListener(this);
        editText_Date.setOnClickListener(this);
        imageTime.setOnClickListener(this);
        editText_Time.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId() ){
            case  R.id.imageCalender  :
                setDateFromDatePicker();
                break;
            case  R.id.editText_Date  :
                setDateFromDatePicker();
                break;
            case R.id.imageTime :
                callTimePickerActivity();
                break;
            case R.id.editText_Time :
                callTimePickerActivity();
                break;
        }
    }

    private void setDateFromDatePicker(){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog( AddExpenses.this,android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,dateSetListener,year,month,day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day+"/"+month+"/"+year;
                editText_Date.setText(date);
            }
        } ;
    }

    public void callTimePickerActivity(){
        Intent intent = new Intent(getApplicationContext(), TimePickActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                editText_Time.setText(data.getStringExtra(TimePickActivity.TIME_PASS_KEY));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = getSharedPreferences(TimePickActivity.TIME_PASS_KEY_PREFERENCE, 0);
        String  dataFromOtherAct= sp.getString(TimePickActivity.TIME_PASS_KEY, "");
        if(!dataFromOtherAct.isEmpty()){
            editText_Time.setText(dataFromOtherAct);
        }
    }
}

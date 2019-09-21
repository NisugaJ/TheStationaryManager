package com.example.stationerymanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class UpdateExpense extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_Title;
    private EditText editText_Amount;
    private EditText editText_NoteDescription;
    private EditText editText_Date;
    private ImageView imageCalender;
    private EditText editText_Time;
    private ImageView imageTime;
    private Spinner expenseTypesSpinner;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePicker timePicker;
    private Button btnAddExpense;

    private String dateANDtime;
    private ArrayAdapter<String> SpinnerAdapter;
    private int currentId ;

    public  static SQLiteExpensesHelper sqLiteExpensesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_expenses);
        setTitle(R.string.update_expense);

        Intent updateIntent = getIntent();


        editText_Title = findViewById(R.id.titleInput);
        imageCalender = findViewById(R.id.imageCalender);
        editText_Date = findViewById(R.id.editText_Date);
        imageTime = findViewById(R.id.imageTime);
        editText_Time = findViewById(R.id.editText_Time);
        expenseTypesSpinner = findViewById((R.id.spinner_ExpenseTypes));
        editText_Amount = findViewById(R.id.amountInput);
        editText_NoteDescription = findViewById(R.id.noteInput);
        loadExpenseTypesToSpinner();


        currentId = updateIntent.getIntExtra("id", -99999999);
        editText_Title.setText( updateIntent.getStringExtra("title"));
        expenseTypesSpinner.setSelection(SpinnerAdapter.getPosition(updateIntent.getStringExtra("type")));
        editText_Amount.setText( updateIntent.getStringExtra("amount"));
        editText_Date.setText( updateIntent.getStringExtra("date"));
        editText_Time.setText( updateIntent.getStringExtra("time"));
        editText_NoteDescription.setText( updateIntent.getStringExtra("note"));

        btnAddExpense = findViewById(R.id.btnUpdateExpense);

        imageCalender.setOnClickListener(this);
        editText_Date.setOnClickListener(this);
        imageTime.setOnClickListener(this);
        editText_Time.setOnClickListener(this);
        btnAddExpense.setOnClickListener(this);
        //connect to db
        sqLiteExpensesHelper = new SQLiteExpensesHelper( getApplicationContext(), "Stationery.db", null, 1);

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
            case R.id.btnUpdateExpense:
                if(validateForm())
                    executeUpdateExpense();
                break;
        }
    }





    //this sets date to the form from DATE UI
    private void setDateFromDatePicker(){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog( UpdateExpense.this,android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,dateSetListener,year,month,day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String monthOK = String.valueOf(month);
                String dayOK = String.valueOf(dayOfMonth);
                if(monthOK.length() == 1){
                    monthOK = "0"+ monthOK;
                }
                if(dayOK.length() == 1){
                    dayOK = "0"+ dayOK;
                }
                String Year = String.valueOf(year);
                dateANDtime = Year+"-"+monthOK+"-"+dayOK;
                Log.i("DateTime", dateANDtime);
                editText_Date.setText(dateANDtime);
            }
        } ;
    }

    //starts Clock UI
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

    //Loads data to ExpenseTypes spinner
    private void loadExpenseTypesToSpinner(){
        SpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Expenses.expenseTypes);
        SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expenseTypesSpinner.setAdapter(SpinnerAdapter);    }

    //Validates all the input fields
    private boolean validateForm(){
        dateANDtime = editText_Date.getText().toString().trim() + " "+ editText_Time.getText().toString().trim();
        Log.i("DateTime", dateANDtime);
        if (editText_Title.getText().toString().trim().length() == 0){
            Toast.makeText(getApplicationContext(),"Please enter a Title", Toast.LENGTH_SHORT).show();
            return false;
        }

        String dateTimeRegex = "(?:2100|20[0-9]{2})-(?:0?2-(?:[12][0-9]|0?[1-9])|(?:0?[469]|11)-(?:30|[12][0-9]|0?[1-9])|(?:0?[13578]|1[02])-(?:3[01]|[12][0-9]|0?[1-9]))[ \\t]+(?:2[0-3]|[01]?[0-9])[:.][0-5]?[0-9]";
        if(!dateANDtime.matches(dateTimeRegex) ){
            Toast.makeText(getApplicationContext(),"Please use this format (YYYY-MM-DD), (HH:MM)", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editText_Amount.getText().toString().trim().length() == 0){
            Toast.makeText(getApplicationContext(),"Please enter an Amount", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    //executes insertFunction of sqLiteExpensesHelper
    private void executeUpdateExpense(){
        try {

            sqLiteExpensesHelper.updateExpense(currentId,
                    editText_Title.getText().toString().trim(),
                    expenseTypesSpinner.getSelectedItem().toString().trim(),
                    editText_Amount.getText().toString().trim(),
                    dateANDtime,
                    editText_NoteDescription.getText().toString().trim()
            );

            Toast.makeText(getApplicationContext(),"Updated Successfully", Toast.LENGTH_SHORT).show();
//            Intent returnIntent = new Intent(this, ExpensesList.class);
////            returnIntent.putExtra("expenseType",expenseTypesSpinner.getSelectedItem().toString().trim());
////            startActivity(returnIntent);
            finish();
        }catch (Exception e){
            Log.e("CRUD error", e.toString());
            e.printStackTrace();
        }

    }



}

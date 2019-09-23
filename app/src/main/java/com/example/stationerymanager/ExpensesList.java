package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExpensesList extends AppCompatActivity  {

    ListView listView;
    ArrayList<ExpenseModel> expenseList;
    ArrayAdapter<String> SpinnerAdaptor;
    Spinner expenseType;
    SQLiteExpensesHelper sqLiteExpensesHelper;
    private String currentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);
        setTitle("Previous Expenses");

        expenseType = findViewById(R.id.spinnerExpenses);
        listView=findViewById(R.id.expenses_list);
        loadExpenseTypesToSpinner();

        Intent intent = getIntent();
        currentType =  intent.getStringExtra("expenseType");
        expenseType.setSelection(SpinnerAdaptor.getPosition(currentType));

        sqLiteExpensesHelper = new SQLiteExpensesHelper( getApplicationContext(), "Stationery.db", null, 1);

        loadExpensesList(currentType);
    }


    //Loads data to ExpenseTypes spinner
    private void loadExpenseTypesToSpinner(){
        SpinnerAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Expenses.expenseTypes);
        SpinnerAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expenseType.setAdapter(SpinnerAdaptor);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("Restarting");
        recreate();
    }


    @Override
    protected void onResume() {
        super.onResume();

        expenseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadExpensesList( expenseType.getItemAtPosition(i).toString().trim());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void loadExpensesList(String expenseType){
        expenseList = new ArrayList<>();
        ExpensesListAdapter adapter=new ExpensesListAdapter(this, R.layout.expenses_list_layout_single, expenseList );
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Cursor cursor = sqLiteExpensesHelper.getExpenses("SELECT * FROM expenses WHERE type = '"+ expenseType+"';");
//      Cursor cursor = sqLiteExpensesHelper.getExpenses("SELECT * FROM expenses");
        expenseList.clear();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String type = cursor.getString(2);
            String amount = cursor.getString(3);
            String dateANDtime = cursor.getString(4);
            String note = cursor.getString(5);

            ExpenseModel expenseModel = new ExpenseModel( id, title, type,amount ,dateANDtime,note );

            expenseList.add(expenseModel);
            System.out.println("expenseModel ID " + expenseModel.getId());
        }

        adapter.notifyDataSetChanged();
        if(expenseList.size() == 0 ){
            Toast.makeText(getApplicationContext(), "No expenses found", Toast.LENGTH_SHORT).show();
        }

    }
}

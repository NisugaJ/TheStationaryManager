package com.example.stationerymanager;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import static com.example.stationerymanager.R.id.floatingActionButton_addExpense;
import static com.example.stationerymanager.R.id.gridExpenseTypes;


public class Expenses extends Fragment {
    View view;
    FloatingActionButton addExpense;
    GridView grid;
    static String[] expenseTypes = {
            "Rent",
            "Repair",
            "Legal",
            "Transport",
            "Tax",
            "Ads",
            "Electricity",
            "Other",
    };

    String[] expenseTypesAmounts = {
            "2500.00",
            "3000.00",
            "524.65",
            "600.00",
            "451.00",
            "200.00",
            "2100.00",
            "400.00"
    };

    int[] imageIds = {
            R.drawable.ic_rent,
            R.drawable.ic_repair,
            R.drawable.ic_legal,
            R.drawable.ic_transport,
            R.drawable.ic_tax,
            R.drawable.ic_ads,
            R.drawable.ic_electricity,
            R.drawable.ic_other

    };

    public  static SQLiteExpensesHelper sqLiteExpensesHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
        ExpenseTypesGrid adapter = new ExpenseTypesGrid(context,expenseTypes,  expenseTypesAmounts, imageIds );
        view = inflater.inflate(R.layout.fragment_expenses, container, false);
        grid = view.findViewById(gridExpenseTypes);
        addExpense = view.findViewById(floatingActionButton_addExpense);

        //db connection creation
        sqLiteExpensesHelper = new SQLiteExpensesHelper( context, "Stationery.db", null, 1);

        //creating table if not created
        sqLiteExpensesHelper.creatExpensesTable("CREATE TABLE  IF NOT EXISTS expenses (\n" +
                                                            "\texpenseID integer PRIMARY KEY AUTOINCREMENT,\n" +
                                                            "  \ttitle TEXT,\n" +
                                                            "  \ttype TEXT,\n" +
                                                            "  \tamount REAL,\n" +
                                                            "  \tdateANDtime TEXT,\n" +
                                                            "  \tnote TEXT\n" +
                                                            ")");

        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getContext(), expenseTypes[i]+ " Clicked", Toast.LENGTH_SHORT).show();
                    Intent intentToExpensesList = new Intent(getContext(), ExpensesList.class);
                    intentToExpensesList.putExtra("expenseType", expenseTypes[i]);
                    startActivity(intentToExpensesList);
                }
            });

        addExpense.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AddExpenses.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
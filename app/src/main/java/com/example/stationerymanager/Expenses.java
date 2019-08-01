package com.example.stationerymanager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import static com.example.stationerymanager.R.id.add;
import static com.example.stationerymanager.R.id.floatingActionButton_addExpense;
import static com.example.stationerymanager.R.id.gridExpenseTypes;
import static com.example.stationerymanager.R.id.start;


public class Expenses extends Fragment {
    View view;
    FloatingActionButton addExpense;
    GridView grid;
    String[] expenseTypes = {
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
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getContext(), expenseTypes[i]+ " Clicked", Toast.LENGTH_SHORT).show();
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
}
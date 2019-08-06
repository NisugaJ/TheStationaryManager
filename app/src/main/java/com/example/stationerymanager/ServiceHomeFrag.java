package com.example.stationerymanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import static com.example.stationerymanager.R.id.floatingActionButton_addExpense;
import static com.example.stationerymanager.R.id.gridExpenseTypes;


public class ServiceHomeFrag extends Fragment {    View view;
    FloatingActionButton addExpense;
    GridView grid;
    String[] expenseTypes = {
            "Binding",
            "Photo Copy",
            "PrintOuts",
            "Laminating",

    };



    int[] imageIds = {
            R.drawable.binding,
            R.drawable.photo,
            R.drawable.printer,
            R.drawable.lam,


    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
        ServiceTypesGrid adapter = new ServiceTypesGrid(context,expenseTypes, imageIds );
        view = inflater.inflate(R.layout.fragment_service_home, container, false);
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
                Intent intent = new Intent(view.getContext(), AddService1.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}

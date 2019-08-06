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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.stationerymanager.R.id.floatingActionButton_addSales;
import static com.example.stationerymanager.R.id.floatingActionButton_updateProducts;


public class SalesList extends Fragment {
    View vw;
    FloatingActionButton addSales;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vw = inflater.inflate(R.layout.fragment_sales_list, container, false);

        addSales = vw.findViewById(floatingActionButton_addSales);

        addSales.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(vw.getContext(), AddSales.class);
                startActivity(intent);
            }
        });

       return vw;
    }
}

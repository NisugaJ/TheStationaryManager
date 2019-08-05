package com.example.stationerymanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.stationerymanager.R.id.floatingActionButton_addExpense;
import static com.example.stationerymanager.R.id.floatingActionButton_addProducts;


public class Stationery extends Fragment {
    View vw;
    FloatingActionButton addProducts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vw = inflater.inflate(R.layout.fragment_stationery, container, false);

        addProducts = vw.findViewById(floatingActionButton_addProducts);

        addProducts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(vw.getContext(), AddProducts.class);
                startActivity(intent);
            }
        });

        return vw;

    }



}

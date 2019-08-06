package com.example.stationerymanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.stationerymanager.R.id.floatingActionButton_addExpense;
import static com.example.stationerymanager.R.id.floatingActionButton_addProducts;


public class Stationery extends Fragment {
    View vw;
    FloatingActionButton addProducts;
    Button btnNav2;
    TextView tx;
    TextView tx1;
    TextView tx2;
    TextView tx3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vw = inflater.inflate(R.layout.fragment_stationery, container, false);

        btnNav2 = (Button)vw.findViewById(R.id.btnNav2);
        btnNav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                ft1.replace(R.id.fragment2, new SalesList());
                ft1.commit();
            }
        });

        tx = (TextView)vw.findViewById(R.id.tx);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment2, new BooksList());
                ft.commit();
            }
        });

        tx1 = (TextView)vw.findViewById(R.id.tx1);
        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fragment2, new PensList());
                ft2.commit();
            }
        });

        tx2 = (TextView)vw.findViewById(R.id.tx2);
        tx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                ft3.replace(R.id.fragment2, new SchoolItemsList());
                ft3.commit();
            }
        });

        tx3 = (TextView)vw.findViewById(R.id.tx3);
        tx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft4 = getFragmentManager().beginTransaction();
                ft4.replace(R.id.fragment2, new OfficeItemsList());
                ft4.commit();
            }
        });



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

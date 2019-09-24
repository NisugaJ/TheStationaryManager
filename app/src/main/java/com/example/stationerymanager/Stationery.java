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
        final String[] productsArr = getContext().getResources().getStringArray(R.array.combo_box1);

        btnNav2 = vw.findViewById(R.id.btnNav2);
        btnNav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(vw.getContext(), AddSales.class);
                startActivity(intent);
            }
        });

        tx = (TextView)vw.findViewById(R.id.tx);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(vw.getContext(), ProductListActivity.class);
                intent.putExtra("product_type", productsArr[0] );
                startActivity(intent);
            }
        });

        tx1 = (TextView)vw.findViewById(R.id.tx1);
        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vw.getContext(), ProductListActivity.class);
                intent.putExtra("product_type", productsArr[1] );
                startActivity(intent);
            }
        });

        tx2 = (TextView)vw.findViewById(R.id.tx2);
        tx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vw.getContext(), ProductListActivity.class);
                intent.putExtra("product_type", productsArr[2] );
                startActivity(intent);
            }
        });

        tx3 = (TextView)vw.findViewById(R.id.tx3);
        tx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vw.getContext(), ProductListActivity.class);
                intent.putExtra("product_type", productsArr[3] );
                startActivity(intent);
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

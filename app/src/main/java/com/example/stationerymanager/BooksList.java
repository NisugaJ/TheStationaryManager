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

import static com.example.stationerymanager.R.id.floatingActionButton_addProducts;
import static com.example.stationerymanager.R.id.floatingActionButton_updateProducts;


public class BooksList extends Fragment {
    View vw;
    FloatingActionButton updateProducts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vw = inflater.inflate(R.layout.fragment_books_list, container, false);

        updateProducts = vw.findViewById(floatingActionButton_updateProducts);

        updateProducts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(vw.getContext(), UpdateProducts.class);
                startActivity(intent);
            }
        });

        return vw;

    }
}

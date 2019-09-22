package com.example.stationerymanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.stationerymanager.R.id.floatingActionButton_addServices;


public class Services extends Fragment {
    View vw;
    FloatingActionButton addServices;
    Button SbtnNav2;
    TextView Stx;
    TextView Stx1;
    TextView Stx2;
    TextView Stx3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vw = inflater.inflate(R.layout.fragment_services, container, false);
        final String[] servicesArr = getContext().getResources().getStringArray(R.array.comboboxadd1);

        SbtnNav2 = vw.findViewById(R.id.SbtnNav2);
        SbtnNav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vw.getContext(), AddServiceSales.class);
                startActivity(intent);
            }
        });

        Stx = (TextView)vw.findViewById(R.id.Stx);
        Stx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vw.getContext(), BindingDisplay.class);
                intent.putExtra("serviceName", servicesArr[0]);
                startActivity(intent);
            }
        });

        Stx1 = (TextView)vw.findViewById(R.id.Stx1);
        Stx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vw.getContext(), BindingDisplay.class);
                intent.putExtra("serviceName", servicesArr[2]);
                startActivity(intent);
            }
        });

        Stx2 = (TextView)vw.findViewById(R.id.Stx2);
        Stx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vw.getContext(), BindingDisplay.class);
                intent.putExtra("serviceName", servicesArr[1]);

                startActivity(intent);
            }
        });

        Stx3 = (TextView)vw.findViewById(R.id.Stx3);
        Stx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vw.getContext(), BindingDisplay.class);
                intent.putExtra("serviceName", servicesArr[3]);

                startActivity(intent);
            }
        });



        addServices = vw.findViewById(floatingActionButton_addServices);

        addServices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(vw.getContext(), AddService1.class);
                startActivity(intent);
            }
        });

        return vw;
    }

}

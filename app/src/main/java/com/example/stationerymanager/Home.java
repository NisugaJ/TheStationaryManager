package com.example.stationerymanager;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Home extends Fragment {
    View view;
    CardView toStationery;
    CardView toPrinting;
    CardView toFaxAndCalls;
    CardView toExpenses;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        toStationery = view.findViewById(R.id.stationeryCard);
        toPrinting = view.findViewById(R.id.printingCard);
        toFaxAndCalls = view.findViewById(R.id.faxAndCallsCard);
        toExpenses = view.findViewById(R.id.expensesCard);
        return  view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        toStationery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Stationery stationery = new Stationery();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment2, stationery);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });
//    }
}

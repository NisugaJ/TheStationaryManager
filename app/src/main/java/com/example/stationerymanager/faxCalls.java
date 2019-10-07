package com.example.stationerymanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class faxCalls extends Fragment {
    private View view;
    private Button call;
    private Button fax;


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fax_calls, container, false);
        call = view.findViewById(R.id.call);
        fax = view.findViewById(R.id.fax);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),Calls.class);
                startActivity(intent);
            }
        });
        fax.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),Fax.class);
                startActivity(intent);
            }
        });
    }


}

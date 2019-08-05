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


public class faxCalls extends Fragment {
    View view;
    Button call;
    Button fax;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fax_calls, container, false);
        call = v.findViewById(R.id.addCalls);
        fax = v.findViewById(R.id.addFax);

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
        return v;
    }
}

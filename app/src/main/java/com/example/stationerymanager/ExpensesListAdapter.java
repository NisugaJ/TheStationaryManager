package com.example.stationerymanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ExpensesListAdapter extends ArrayAdapter<ExpenseModel> {

    private final Context context;
    List<ExpenseModel> expenses;
    int layoutRes;

    public ExpensesListAdapter(Context context, int layoutRes, List<ExpenseModel> expenses){
        super(context,layoutRes, expenses);

        this.context = context;
        this.layoutRes  = layoutRes;
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(layoutRes, null);

        TextView typeView = rowView.findViewById(R.id.typeView);
        TextView descView = rowView.findViewById(R.id.descView);
        TextView timeView = rowView.findViewById(R.id.timestampView);
        TextView amountView = rowView.findViewById(R.id.amountView);

        ExpenseModel expenseItem = expenses.get(position);

        typeView.setText(expenseItem.getType());
        descView.setText(expenseItem.getNoteDescription());
        timeView.setText(expenseItem.getTimestamp());
        amountView.setText(expenseItem.getAmount());

        return  rowView;
    }


}

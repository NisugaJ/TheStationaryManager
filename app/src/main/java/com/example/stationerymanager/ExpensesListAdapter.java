package com.example.stationerymanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ImageViewCompat;

import java.util.ArrayList;
import java.util.List;

public class ExpensesListAdapter extends ArrayAdapter<ExpenseModel> {

    private final Context context;
    ArrayList<ExpenseModel> expenses;
    int layoutRes;

    public ExpensesListAdapter(Context context, int layoutRes, ArrayList<ExpenseModel> expenses){
        super(context,layoutRes, expenses);

        this.context = context;
        this.layoutRes  = layoutRes;
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public View getView(int position, View view, final ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(layoutRes, null);

        TextView titleView = rowView.findViewById(R.id.title);
        TextView descView = rowView.findViewById(R.id.descView);
        TextView timeView = rowView.findViewById(R.id.timestampView);
        TextView amountView = rowView.findViewById(R.id.amountView);
        ImageView update = rowView.findViewById(R.id.editExpenseBtn);
        ImageView delete = rowView.findViewById(R.id.deleteExpenseBtn);

        final ExpenseModel expenseItem = expenses.get(position);
        titleView.setText(expenseItem.getTitle());
        descView.setText(expenseItem.getNoteDescription());
        timeView.setText(expenseItem.getTimestamp());
        amountView.setText(expenseItem.getAmount());

        update.setOnClickListener(  new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent updateIntent = new Intent(getContext(), UpdateExpense.class);
                  updateIntent.putExtra("id", expenseItem.getId());
                  updateIntent.putExtra("title", expenseItem.getTitle());
                  updateIntent.putExtra("type", expenseItem.getType());
                  updateIntent.putExtra("amount", expenseItem.getAmount());
                  updateIntent.putExtra("date", expenseItem.splitDateTime()[0]);
                  updateIntent.putExtra("time", expenseItem.splitDateTime()[1]);
                  updateIntent.putExtra("note", expenseItem.getNoteDescription());

                  view.getContext().startActivity(updateIntent);

              }
          }
        );

        delete.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                  builder.setMessage(R.string.delete_alert)
                          .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                              public void onClick(DialogInterface dialog, int id) {
                                  try {
                                      Expenses.sqLiteExpensesHelper.deleteExpense(expenseItem.getId());
                                      Toast.makeText(getContext(),"Deleted Successfully", Toast.LENGTH_SHORT).show();
                                      remove(expenseItem);
                                  }catch (Exception e){
                                      e.printStackTrace();
                                      Log.e("CRUD error", e.toString());
                                  }
                              }
                          })
                          .setNegativeButton("No", new DialogInterface.OnClickListener() {
                              public void onClick(DialogInterface dialog, int id) {
                                  dialog.cancel();
                              }
                          }).show();

              }

          }
        );


        return  rowView;
    }

    @Override
    public void remove(@Nullable ExpenseModel object) {
        super.remove(object);
    }
}

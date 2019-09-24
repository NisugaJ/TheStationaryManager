package com.example.stationerymanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class SalesListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<sModel> salesList;

    public SalesListAdapter(Context context, int layout, ArrayList<sModel> salesList) {
        this.context = context;
        this.layout = layout;
        this.salesList = salesList;
    }

    @Override
    public int getCount() {
        return salesList.size();
    }

    @Override
    public Object getItem(int i) {
        return salesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txtDate, txtCode, txtName, txtPrice, txtQty;
        ImageView editSSalesBtn, deleteSSalesBtn;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View salesrow = inflater.inflate(layout, null);
        holder.txtDate = salesrow.findViewById(R.id.txtDate);
        holder.txtCode = salesrow.findViewById(R.id.txtCode);
        holder.txtName = salesrow.findViewById(R.id.txtName);
        holder.txtPrice = salesrow.findViewById(R.id.txtPrice);
        holder.txtQty = salesrow.findViewById(R.id.txtQty);
        holder.editSSalesBtn = salesrow.findViewById(R.id.editSSalesBtn);
        holder.deleteSSalesBtn = salesrow.findViewById(R.id.deleteSSalesBtn);

        salesrow.setTag(holder);

        holder = (ViewHolder) salesrow.getTag();


        final sModel model = salesList.get(i);

        holder.txtDate.setText(model.getDATE());
        holder.txtCode.setText(model.getCODE());
        holder.txtName.setText(model.getNAME());
        holder.txtPrice.setText(model.getPRICE());
        holder.txtQty.setText(model.getQUANTITY());



        holder.editSSalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Hi", Toast.LENGTH_SHORT).show();


                Intent updateSintent = new Intent(context, UpdateSales.class);
                updateSintent.putExtra("ID", model.getID());
                updateSintent.putExtra("DATE", model.getDATE());
                updateSintent.putExtra("CODE", model.getCODE());
                updateSintent.putExtra("NAME", model.getNAME());
                updateSintent.putExtra("PRICE", model.getPRICE());
                updateSintent.putExtra("QUANTITY", model.getQUANTITY());

                context.startActivity(updateSintent);

            }
        });



        holder.deleteSSalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogDelete = new AlertDialog.Builder(context);
                dialogDelete.setTitle("WARNING!!!");
                dialogDelete.setMessage("Are you sure to Delete ?");
                dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            AddSales.myDb.deleteData(model.getID());
                            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            salesrow.invalidate();
                        } catch (Exception e) {
                            Log.e("Deleting Error", e.getMessage());

                        }
                    }
                });

                dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                dialogDelete.show();
            }
        });

        return salesrow;
    }

}

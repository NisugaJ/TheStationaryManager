package com.example.stationerymanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class ServiceAdapter extends BaseAdapter {

    private Context context;
        private int layout;
        private ArrayList<Model> recordList;

    public ServiceAdapter(Context context, int layout, ArrayList<Model> recordList) {
            this.context = context;
            this.layout = layout;
            this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        TextView txtDate, txtSername, txtCategory, txtPrice, txtQuantity, txtProfit;
       ImageView deleteServiceSalesBtn,editServiceBtn;
    }



    @Override
    public View getView(int i, final View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
           holder.txtDate = row.findViewById(R.id.txtDate);
            holder.txtSername = row.findViewById(R.id.txtSername);
            holder.txtCategory = row.findViewById(R.id.txtCategory);
            holder.txtPrice = row.findViewById(R.id.txtPrice);
            holder.txtQuantity = row.findViewById(R.id.txtQuantity);
            holder.txtProfit = row.findViewById(R.id.txtProfit);
            holder.deleteServiceSalesBtn = row.findViewById(R.id.deleteServiceSalesBtn);
            holder.editServiceBtn = row.findViewById(R.id.editServiceBtn);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }
        final Model model = recordList.get(i);

        holder.txtDate.setText(model.getDate());
        holder.txtSername.setText(model.getServiceName());
        holder.txtCategory.setText(model.getCategory());
        holder.txtPrice.setText(model.getPrice());
        holder.txtQuantity.setText(model.getQuantity());
        holder.txtProfit.setText(model.getProfit());


      holder.editServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(context, ServiceSaleUpdate1.class);
                updateIntent.putExtra("id", model.getId());
                updateIntent.putExtra("Date", model.getDate());
                updateIntent.putExtra("ServiceName", model.getServiceName());
                updateIntent.putExtra("Price", model.getPrice());
                updateIntent.putExtra("Quantity", model.getQuantity());
                updateIntent.putExtra("Profit", model.getProfit());
                updateIntent.putExtra("category", model.getCategory());

                context.startActivity(updateIntent);

            }
        });

        holder.deleteServiceSalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure want to delete ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    AddServiceSales.nDataBaseHelper.deleteData(model.getId());
                                    Toast.makeText(context,"Deleted Successfully", Toast.LENGTH_SHORT).show();
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
        });


        return row;
    }


}

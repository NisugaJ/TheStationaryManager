package com.example.stationerymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ServiceAdapterNew extends BaseAdapter {

    private Context context;
    private int layoutNew;
    private ArrayList<Model_new> recordListNew;

    public ServiceAdapterNew(Context context, int layoutNew, ArrayList<Model_new> recordListNew) {
        this.context = context;
        this.layoutNew = layoutNew;
        this.recordListNew = recordListNew;
    }

    @Override
    public int getCount() {
        return recordListNew.size();
    }

    @Override
    public Object getItem(int j) {
        return recordListNew.get(j);
    }

    @Override
    public long getItemId(int j) {
        return j;
    }
    private class ViewHolderNew {
        TextView txtServiceName, txtServiceType, txtDescription, txtCostPrice, txtSellingPrice, txtQuantity;
    }

    @Override
    public View getView(int j, View newView, ViewGroup parent) {
        View row = newView;
        ServiceAdapterNew.ViewHolderNew holder = new ServiceAdapterNew.ViewHolderNew();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutNew, null);

            holder.txtServiceName = row.findViewById(R.id.txtServiceName);
            holder.txtServiceType = row.findViewById(R.id.txtServiceType);
            holder.txtDescription = row.findViewById(R.id.txtDescription);
            holder.txtCostPrice = row.findViewById(R.id.txtCostPrice);
            holder.txtSellingPrice = row.findViewById(R.id.txtSellingPrice);
            holder.txtQuantity = row.findViewById(R.id.txtQuan);
            row.setTag(holder);

        } else {
            holder = (ServiceAdapterNew.ViewHolderNew) row.getTag();
        }
        Model_new modelnew = recordListNew.get(j);

        holder.txtServiceName.setText(modelnew.getServiceName());
        holder.txtServiceType.setText(modelnew.getServiceType());
        holder.txtDescription.setText(modelnew.getDescription());
        holder.txtCostPrice.setText(modelnew.getCostPrice());
        holder.txtSellingPrice.setText(modelnew.getSellingPrice());
        holder.txtQuantity.setText(modelnew.getQuantity());


        return row;
    }
}

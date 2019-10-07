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

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class ProductsListAdapter extends BaseAdapter {

    Context context;
    private int playout;
    private ArrayList<pModel> productList;

    public ProductsListAdapter(Context context, int playout, ArrayList<pModel> productList) {
        this.context = context;
        this.playout = playout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    private class pViewHolder{
        TextView txtPcategory, txtPcode, txtPname, txtPcPrice, txtPsPrice, txtPQty;
        ImageView editProductsBtn, deleteProductsBtn;


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        pViewHolder pholder = new pViewHolder();


        LayoutInflater pinflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View prow = pinflater.inflate(playout, null);

        pholder.txtPcategory = prow.findViewById(R.id.txtPcategory);
        pholder.txtPcode = prow.findViewById(R.id.txtPcode);
        pholder.txtPname = prow.findViewById(R.id.txtPname);
        pholder.txtPcPrice = prow.findViewById(R.id.txtPcPrice);
        pholder.txtPsPrice = prow.findViewById(R.id.txtPsPrice);
        pholder.txtPQty = prow.findViewById(R.id.txtPQty);
        pholder.deleteProductsBtn = prow.findViewById(R.id.deleteProductsBtn);
        pholder.editProductsBtn = prow.findViewById(R.id.editProductsBtn);

        prow.setTag(pholder);




        pholder = (pViewHolder)prow.getTag();


        final pModel pmodel = productList.get(i);

        pholder.txtPcategory.setText(pmodel.getCategory());
        pholder.txtPcode.setText(pmodel.getCode());
        pholder.txtPname.setText(pmodel.getNmae());
        pholder.txtPcPrice.setText(pmodel.getcPrice());
        pholder.txtPsPrice.setText(pmodel.getsPrice());
        pholder.txtPQty.setText(pmodel.getQuantity());


        pholder.deleteProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogDelete = new AlertDialog.Builder(context);
                dialogDelete.setTitle("WARNING!!!");
                dialogDelete.setMessage("Are you sure to Delete ?");
                dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            AddProducts.spDb.deleteDataProduct(pmodel.getId());
                            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            prow.invalidate();
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


        pholder.editProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updatePintent = new Intent(context, UpdateProducts.class);
                updatePintent.putExtra("id", pmodel.getId());
                updatePintent.putExtra("category", pmodel.getCategory());
                updatePintent.putExtra("code", pmodel.getCode());
                updatePintent.putExtra("name", pmodel.getNmae());
                updatePintent.putExtra("cPrice", pmodel.getcPrice());
                updatePintent.putExtra("sPrice", pmodel.getsPrice());
                updatePintent.putExtra("quantity", pmodel.getQuantity());

                context.startActivity(updatePintent);

            }
        });


        return prow;
    }



}

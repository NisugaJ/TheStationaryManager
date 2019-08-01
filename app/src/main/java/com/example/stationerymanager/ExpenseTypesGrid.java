package com.example.stationerymanager;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpenseTypesGrid extends BaseAdapter {

    private Context mContext;
    private final String[] expenseTypes;
    private final String[] expenseTypesAmounts;
    private final int[] ImageId;

    public ExpenseTypesGrid(Context c,String[] expenseTypes, String[] expenseTypesAmounts,int[] ImageId ) {
        mContext = c;
        this.ImageId = ImageId;
        this.expenseTypes = expenseTypes;
        this.expenseTypesAmounts = expenseTypesAmounts;
    }

    @Override
    public int getCount() {
        return expenseTypes.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater)mContext.
                                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){

            grid = inflater.inflate(R.layout.expense_type_grid_single, null);
            TextView textView1 = grid.findViewById(R.id.grid_text1);
            TextView textView2 = grid.findViewById(R.id.grid_text2);
            ImageView expenseTypeImage = grid.findViewById(R.id.grid_image);
            textView1.setText(expenseTypes[i]);
            textView2.setText("Rs. "+expenseTypesAmounts[i]);
            expenseTypeImage.setImageResource(ImageId[i]);
        }
        else {
            grid = convertView;
        }
        return grid;
    }
}

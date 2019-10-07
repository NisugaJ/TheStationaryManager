package com.example.stationerymanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class BindingDisplay extends AppCompatActivity {

    ListView mListView;
    ArrayList<Model_new> mList;
    ServiceAdapterNew mAdapter = null;
        ArrayAdapter SpinnerAdapter;

    public static DatabaseHelper mDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_display);


//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Record List");
        Intent intent = getIntent();
        String currentServiceName =  intent.getStringExtra("serviceName");
        setTitle(currentServiceName +" List");
        mListView = findViewById(R.id.listViewNew);
        mList = new ArrayList<>();
        mAdapter = new ServiceAdapterNew(this, R.layout.row_new, mList);
        mListView.setAdapter(mAdapter);
        mDataBaseHelper = new DatabaseHelper(this, "STATIONERYDB.sqlite", null, 2);

        try {
            Cursor cursor = mDataBaseHelper.getDataNew("SELECT * FROM RECORDNew WHERE ServiceName = '"+ currentServiceName +"';");
            mList.clear();
            while (cursor.moveToNext()) {
                int ID = cursor.getInt(0);
                String ServiceName = cursor.getString(1);
                String ServiceType = cursor.getString(2);
                String Description = cursor.getString(3);
                String CostPrice = cursor.getString(4);
                String SellingPrice = cursor.getString(5);
                String Quantity = cursor.getString(6);

                mList.add(new Model_new(ID,ServiceName, ServiceType, Description, CostPrice, SellingPrice, Quantity));
            }
        } catch (SQLiteException e) {
            Log.e("DB error", e.toString());
        }

        mAdapter.notifyDataSetChanged();
        if (mList.size() == 0) {
            Toast.makeText(this, "No Record Found....", Toast.LENGTH_SHORT).show();
        }

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                final CharSequence[] items = {"Update" , "Delete"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(BindingDisplay.this);

                dialog.setTitle("choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        if(j == 0) {
                            Cursor c = mDataBaseHelper.getDataNew("SELECT ID FROM RECORDNew");
                            ArrayList<Integer> arrId = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrId.add(c.getInt(0));
                            }

                            showDialogUpdate(BindingDisplay.this, arrId.get(position));
                        }
                            if (j == 1){
                                //delete
                                Cursor c = mDataBaseHelper.getDataNew("SELECT ID FROM RECORDNew");
                                ArrayList<Integer> arrId = new ArrayList<Integer>();
                                while (c.moveToNext()){
                                    arrId.add(c.getInt(0));
                            }
                                showDialogDelete(arrId.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    private void showDialogDelete(final int IDRecord){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(BindingDisplay.this);
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you need to delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {
                try{
                   // Cursor cursor = mDataBaseHelper.getDataNew("SELECT * FROM RECORDNew WHERE ServiceName = '"+ currentServiceName +"';");
                    mDataBaseHelper.deleteDataNew(IDRecord);
                    Toast.makeText(BindingDisplay.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Log.e("error",e.getMessage());
                }
                updateRecordList();
                recreate();
            }
        });
        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();

    }

    private void showDialogUpdate(Activity activity, final int position){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.service_update_dialog);
        dialog.setTitle("Update");

        final Spinner upspinneradd1 = dialog.findViewById(R.id.upspinneradd1);
        final EditText fillup1 = dialog.findViewById(R.id.fillup1);
        final EditText fillup2 = dialog.findViewById(R.id.fillup2);
        final EditText fillup3 = dialog.findViewById(R.id.fillup3);
        final EditText fillup5 = dialog.findViewById(R.id.fillup5);
        final EditText fillup4 = dialog.findViewById(R.id.fillup4);
        Button btnup1 = dialog.findViewById(R.id.btnup1);


        Cursor cursor = mDataBaseHelper.getDataNew("SELECT * FROM RECORDNew WHERE ID="+position);

        mList.clear();
        while (cursor.moveToNext()) {
            int ID = cursor.getInt(0);
            String ServiceName = cursor.getString(1);
            SpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.comboboxadd1, android.R.layout.simple_spinner_item);
            SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            upspinneradd1.setAdapter(SpinnerAdapter);
            upspinneradd1.setSelection(SpinnerAdapter.getPosition(ServiceName));
            String ServiceType = cursor.getString(2);
            fillup1.setText(ServiceType);
            String Description = cursor.getString(3);
            fillup2.setText(Description);
            String CostPrice = cursor.getString(4);
            fillup3.setText(CostPrice);
            String SellingPrice = cursor.getString(5);
            fillup5.setText(SellingPrice);
            String Quantity = cursor.getString(6);
            fillup4.setText(Quantity);

            mList.add(new Model_new(ID,ServiceName, ServiceType, Description, CostPrice, SellingPrice, Quantity));
        }

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.95);
        int height= (int) (activity.getResources().getDisplayMetrics().heightPixels*0.7);
        dialog.getWindow().setLayout(width,height);
        dialog.show();

        btnup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mDataBaseHelper.updateDataNew(
                            upspinneradd1.getSelectedItem().toString().trim(),
                            fillup1.getText().toString().trim(),
                            fillup2.getText().toString().trim(),
                            fillup3.getText().toString().trim(),
                            fillup5.getText().toString().trim(),
                            fillup4.getText().toString().trim(),
                            position
                            );
                    dialog.dismiss();
                    recreate();
                    Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();


                }
                catch (Exception error){
                    Log.e("Update error",error.getMessage());
                }

                onResume();
                updateRecordList();


            }
        });
    }

    private void updateRecordList() {
        Cursor cursor = mDataBaseHelper.getDataNew("SELECT * FROM RECORDNew");
        mList.clear();
        while (cursor.moveToNext()) {
            int ID = cursor.getInt(0);
            String ServiceName = cursor.getString(1);
            String ServiceType = cursor.getString(2);
            String Description = cursor.getString(3);
            String CostPrice = cursor.getString(4);
            String SellingPrice = cursor.getString(5);
            String Quantity = cursor.getString(6);

            mList.add(new Model_new(ID,ServiceName, ServiceType, Description, CostPrice, SellingPrice, Quantity));
        }
        mAdapter.notifyDataSetChanged();


    }


   /* public void editBinding(View view){
        Intent intent =  new Intent(BindingDisplay.this,ServiceUpdate1.class);
        startActivity(intent);
    }*/

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

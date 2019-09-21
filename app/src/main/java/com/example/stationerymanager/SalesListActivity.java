package com.example.stationerymanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.stationerymanager.AddSales.myDb;

public class SalesListActivity extends AppCompatActivity {

    ListView mListView;
    SalesListAdapter mAdapter = null;
    ArrayList<sModel> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_list);


        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();


        Cursor res = AddSales.myDb.getAllData();
        mList.clear();

        while (res.moveToNext()) {
            String ID = res.getString(0);
            String DATE = res.getString(1);
            String CODE = res.getString(2);
            String NAME = res.getString(3);
            String PRICE = res.getString(4);
            String QUANTITY = res.getString(5);

            mList.add(new sModel(ID, DATE, CODE, NAME, PRICE, QUANTITY));
        }
        mAdapter = new SalesListAdapter(this, R.layout.salesrow, mList);
        mListView.setAdapter(mAdapter);


        mAdapter.notifyDataSetChanged();

        if (mList.size() == 0) {
            Toast.makeText(this, "No DATA Found...", Toast.LENGTH_SHORT).show();
        }

//        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
//                //alert dialog
//
//                CharSequence[] items = {"Update", "Delete"};
//                AlertDialog.Builder dialog = new AlertDialog.Builder(SalesListActivity.this);
//
//                dialog.setTitle("Choose an Action");
//                dialog.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        if(i == 0){
//                            //update
//                            Cursor c = AddSales.myDb.getData("SELECT ID FROM stationerySalesTable" );
//                            ArrayList<String> arrId = new ArrayList<String>();
//
//                            while (c.moveToNext()){
//                                arrId.add(c.getString(0));
//
//                            }
//
//                            //show update dialog
//                            showDialogUpdate(SalesListActivity.this, arrId.get(position));
//
//                        }
//                        if (i == 1){
//                            //delete
//                            Cursor c = AddSales.myDb.getData("SELECT ID FROM stationerySalesTable");
//                            ArrayList<String> arrId = new ArrayList<String>();
//
//                            while (c.moveToNext()){
//                                arrId.add(c.getString(0));
//
//                            }
//
//                            showDialogDelete(arrId.get(i));
//                        }
//                    }
//                });
//
//                dialog.show();
//                return true;
//            }
//        });



    }


//    private void showDialogUpdate(Activity activity, final  String position){
//        final Dialog dialog = new Dialog(activity);
//        dialog.setContentView(R.layout.ssupdate_dialog);
//        dialog.setTitle("UPDATE");
//
//        final EditText editText30 = findViewById(R.id.editText30);
//        final EditText SAfill10 = findViewById(R.id.SAfill10);
//        final EditText SAfill20 = findViewById(R.id.SAfill20);
//        final EditText SAfill30 = findViewById(R.id.SAfill30);
//        final EditText SAfill40 = findViewById(R.id.SAfill40);
//        final Button updt = dialog.findViewById(R.id.updt);
//
//        //set width
//        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels*0.95);
//
//        //set height
//        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels*0.8);
//
//        dialog.getWindow().setLayout(width, height);
//        dialog.show();


//        updt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Hi Button", Toast.LENGTH_SHORT).show();
//
//
//                try {
//                    StationarySalesDatabaseHelper ddd = new StationarySalesDatabaseHelper(getApplicationContext());
//                    ddd.updateData(
//                            editText30.getText().toString().trim(),
//                            SAfill10.getText().toString().trim(),
//                            SAfill20.getText().toString().trim(),
//                            SAfill30.getText().toString().trim(),
//                            SAfill40.getText().toString().trim(),
//                            position
//
//                    );
//                    dialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception error){
//                    Log.e("Updating Error", error.getMessage());
//                }
//
//                updateSalesList();
//            }
//        });
//    }

    public void updateSalesList() {
        Cursor cursor = AddSales.myDb.getData("SELECT * FROM stationerySalesTable");
        mList.clear();

        while (cursor.moveToNext()){
            String ID = cursor.getString(0);
            String DATE = cursor.getString(1);
            String CODE = cursor.getString(2);
            String NAME = cursor.getString(3);
            String PRICE = cursor.getString(4);
            String QUANTITY = cursor.getString(5);

            mList.add(new sModel(ID, DATE, CODE, NAME, PRICE, QUANTITY));
        }

        mAdapter.notifyDataSetChanged();
    }
}

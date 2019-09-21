package com.example.stationerymanager;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Calls extends AppCompatActivity {

    callDBHelper callDB;

    EditText name,no,howMuch,mins,id;

    Button add,viewAll,update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);
        setTitle("Calls");

        callDB = new callDBHelper(this);

        name = findViewById(R.id.name_call);
        no = findViewById(R.id.number_call);
        howMuch = findViewById(R.id.how_much_call);
        mins = findViewById(R.id.mins);
        id = findViewById(R.id.id_call);

        add = findViewById(R.id.addCalls);
        viewAll = findViewById(R.id.select_call);
        update = findViewById(R.id.update_call);
        delete = findViewById(R.id.delete_call);

        insertCall();
        selectAll();
        updateFax();
        deleteFax();
    }

    public void insertCall(){
        add.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = callDB.insertData(name.getText().toString(),
                                no.getText().toString(),
                                howMuch.getText().toString(),
                                mins.getText().toString());
                        if(isInserted == true){
                            Toast.makeText(Calls.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Calls.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
    public void selectAll(){

        viewAll.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Cursor info = callDB.getAllData();

                        if (info.getCount() == 0){
                            //show message
                            showMessage("Error","Nothing Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(info.moveToNext()){
                            buffer.append("Id :"+info.getString(0) + "\n");
                            buffer.append("Name :"+info.getString(1) + "\n");
                            buffer.append("Number :"+info.getString(2) + "\n");
                            buffer.append("How much :"+info.getString(3) + "\n");
                            buffer.append("Mins :"+info.getString(4) + "\n\n");
                        }

                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void updateFax() {
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdated = callDB.updateData(id.getText().toString(),
                                name.getText().toString(),
                                no.getText().toString(),
                                howMuch.getText().toString(),
                                mins.getText().toString());
                        if(isUpdated == true){
                            Toast.makeText(Calls.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Calls.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
    public void deleteFax(){
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = callDB.deleteData(id.getText().toString());
                        if (deleteRows > 0){
                            Toast.makeText(Calls.this, "Data deleted", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Calls.this, "Data Not deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}

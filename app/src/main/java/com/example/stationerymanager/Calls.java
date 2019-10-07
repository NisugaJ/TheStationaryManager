package com.example.stationerymanager;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

    TextView total;

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

        total = findViewById(R.id.callTotal);

        add = findViewById(R.id.addCalls);
        viewAll = findViewById(R.id.select_call);
        update = findViewById(R.id.update_call);
        delete = findViewById(R.id.delete_call);

        insertCall();
        selectAll();
        updateCall();
        deleteCall();
    }

    public void insertCall(){
        add.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if(validateAdd()) {

                            int hm = Integer.valueOf(howMuch.getText().toString());
                            int min = Integer.valueOf(mins.getText().toString());

                            String t = String.valueOf(hm*min);

                            total.setText("Rs : " + t);

                            boolean isInserted = callDB.insertData(name.getText().toString(),
                                    no.getText().toString(),
                                    howMuch.getText().toString(),
                                    mins.getText().toString());

                            if (isInserted == true) {
                                Toast.makeText(Calls.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Calls.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Calls.this, "Please,Input Valid data", Toast.LENGTH_SHORT).show();
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
                        showMessage("CALLS",buffer.toString());
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
    public void updateCall() {
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(validateUpdate()) {
                            int hm = Integer.valueOf(howMuch.getText().toString());
                            int min = Integer.valueOf(mins.getText().toString());

                            String t = String.valueOf(hm*min);

                            total.setText("Rs : " + t);

                            boolean isUpdated = callDB.updateData(id.getText().toString(),
                                    name.getText().toString(),
                                    no.getText().toString(),
                                    howMuch.getText().toString(),
                                    mins.getText().toString());
                            if (isUpdated == true) {
                                Toast.makeText(Calls.this, "Data Updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Calls.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Calls.this, "Please,Input Valid data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
    public void deleteCall(){
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(id.length()<1){
                            id.setError("Please enter valid ID");
                        }else {
                            Integer deleteRows = callDB.deleteData(id.getText().toString());
                            if (deleteRows > 0) {
                                Toast.makeText(Calls.this, "Data deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Calls.this, "Data Not deleted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
    }

    public boolean validateAdd(){
        boolean valid = true;

        if(name.length()<1){
            name.setError("Please enter your name");
            valid = false;
        }
        if(no.length()!=10 ){
            no.setError("Please enter valid number");
            valid = false;
        }
        if(howMuch.length()<1){
            howMuch.setError("Please enter how much for a minute");
            valid = false;
        }
        if(mins.length()<1){
            mins.setError("Please enter no of minutes for a call");
            valid = false;
        }
        return valid;
    }
    public boolean validateUpdate(){
        boolean valid = true;

        if(name.length()<1){
            name.setError("Please enter your name");
            valid = false;
        }
        if(no.length()!=10 ){
            no.setError("Please enter valid number");
            valid = false;
        }
        if(howMuch.length()<1){
            howMuch.setError("Please enter how much for a minute");
            valid = false;
        }
        if(mins.length()<1){
            mins.setError("Please enter no of minutes for a call");
            valid = false;
        }
        if(id.length()<1){
            id.setError("Please enter valid ID");
            valid = false;
        }
        return valid;
    }
}

package com.example.stationerymanager;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Fax extends AppCompatActivity {

    faxDBHelper faxDB;

    EditText name,no,howMuch,noFax,id;

    Button add,viewAll,update,delete;

    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fax);
        setTitle("Fax");

        faxDB = new faxDBHelper(this);

        name = findViewById(R.id.name_fax);
        no = findViewById(R.id.number_fax);
        howMuch = findViewById(R.id.how_much_fax);
        noFax = findViewById(R.id.no_of_fax);
        id = findViewById(R.id.id_fax);

        total = findViewById(R.id.faxTotal);

        add = findViewById(R.id.addFax);
        viewAll = findViewById(R.id.select_all_fax);
        update = findViewById(R.id.update_fax);
        delete = findViewById(R.id.delete_fax);

        insertFax();
        selectAll();
        updateFax();
        deleteFax();
    }

    public void insertFax(){
        add.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if(validateAdd()) {

                            int hm = Integer.valueOf(howMuch.getText().toString());
                            int min = Integer.valueOf(noFax.getText().toString());

                            String t = String.valueOf(hm*min);

                            total.setText("Rs : " + t);

                            boolean isInserted = faxDB.insertData(name.getText().toString(),
                                    no.getText().toString(),
                                    howMuch.getText().toString(),
                                    noFax.getText().toString());
                            if (isInserted == true) {
                                Toast.makeText(Fax.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Fax.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                            }
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
                        Cursor info = faxDB.getAllData();

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
                            buffer.append("No of fax :"+info.getString(4) + "\n\n");
                        }

                        //show all data
                        showMessage("FAX",buffer.toString());
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
                        if(validateUpdate()) {
                            int hm = Integer.valueOf(howMuch.getText().toString());
                            int nf = Integer.valueOf(noFax.getText().toString());

                            String t = String.valueOf(hm*nf);

                            total.setText("Rs : " + t);
                            boolean isUpdated = faxDB.updateData(id.getText().toString(),
                                    name.getText().toString(),
                                    no.getText().toString(),
                                    howMuch.getText().toString(),
                                    noFax.getText().toString());
                            if (isUpdated == true) {
                                Toast.makeText(Fax.this, "Data Updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Fax.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                            }
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
                        if(id.length()<1){
                            id.setError("Please enter valid ID");
                        }else {
                            Integer deleteRows = faxDB.deleteData(id.getText().toString());
                            if (deleteRows > 0) {
                                Toast.makeText(Fax.this, "Data deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Fax.this, "Data Not deleted", Toast.LENGTH_SHORT).show();
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
        if(noFax.length()<1){
            noFax.setError("Please enter no of minutes for a call");
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
        if(noFax.length()<1){
            noFax.setError("Please enter no of minutes for a call");
            valid = false;
        }
        if(id.length()<1){
            id.setError("Please enter valid ID");
            valid = false;
        }
        return valid;
    }
}
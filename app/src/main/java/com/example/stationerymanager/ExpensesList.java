package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ExpensesList extends AppCompatActivity {

    ListView listView;
    List<ExpenseModel> expenseList;
//    String[] types = {
//            "Rent",
//            "Rent",
//            "Rent",
//            "Rent",
//            "Rent",
//            "Rent",
//            "Rent",
//            "Rent",
//    };
//
//    String[] desc = {
//            "Rent1",
//            "Rent2",
//            "Rent3",
//            "Rent4",
//            "Rent5",
//            "Rent6",
//            "Rent7",
//            "Rent8",
//    };
//
//    String[] timestamps = {
//            "2019-02-08  22:00",
//            "2019-02-30  20:00",
//            "2019-02-08  03:00",
//            "2019-02-09  22:00",
//            "2019-04-08  22:00",
//            "2019-02-09  07:00",
//            "2019-02-01  22:00",
//            "2019-09-08  05:00",
//    };
//
//    String[] amounts = {
//            "Rs. 2340.00",
//            "Rs. 264.00",
//            "Rs. 750.00",
//            "Rs. 231.00",
//            "Rs. 244.00",
//            "Rs. 701.00",
//            "Rs. 123.00",
//            "Rs. 100.00",
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);
        setTitle("Previous Expenses");
        listView=findViewById(R.id.expenses_list);
        expenseList = new ArrayList<>();

        //Testing only
        expenseList.add(new ExpenseModel(1, "Rent", "A/C condenser coil repaired ", "2019-07-08", "Rs. 2300.00"));
        expenseList.add(new ExpenseModel(2, "Rent", "A/C condenser coil repaired ", "2018-09-03", "Rs. 250.00"));
        expenseList.add(new ExpenseModel(3, "Rent", "A/C condenser coil repaired ", "2019-07-28", "Rs. 2300.00"));
        expenseList.add(new ExpenseModel(4, "Rent", "A/C condenser coil repaired ", "2019-05-17", "Rs. 900.00"));
        expenseList.add(new ExpenseModel(5, "Rent", "A/C condenser coil repaired ", "2019-10-28", "Rs. 4300.00"));
        expenseList.add(new ExpenseModel(6, "Rent", "A/C condenser coil repaired ", "2019-07-08", "Rs. 230.00"));

        ExpensesListAdapter adapter=new ExpensesListAdapter(this, R.layout.expenses_list_layout_single, expenseList );
        listView.setAdapter(adapter);
    }
}

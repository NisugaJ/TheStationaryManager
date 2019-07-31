package com.example.stationerymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_stationery:
                    mTextMessage.setText(R.string.title_stationery);
                    return true;
                case R.id.navigation_printing:
                    mTextMessage.setText(R.string.title_printing);
                    return true;
                case R.id.navigation_faxAndCalls:
                    mTextMessage.setText(R.string.title_faxAndCalls);
                    return true;
                case R.id.navigation_expenses:
                    mTextMessage.setText(R.string.title_expenses);
                    Intent intent = new Intent(MainActivity.this, AddExpenses.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setTitle(R.string.title_home);
    }

}

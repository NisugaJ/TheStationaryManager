package com.example.stationerymanager;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainNavigation extends AppCompatActivity {
    private ConstraintLayout frag;
    private FragmentManager fm ;
    private FragmentTransaction ft;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle(R.string.title_home);
                    Toast.makeText(getApplicationContext(), "Home Clicked", Toast.LENGTH_SHORT).show();
                    frag = findViewById(R.id.fragment2);
                    frag.removeAllViews();
                    Home homeFrg = new Home();
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    ft.add(R.id.fragment2, homeFrg);
                    ft.commit();
                    return true;
                case R.id.navigation_stationery:
                    setTitle(R.string.title_stationery);
                    frag = findViewById(R.id.fragment2);
                    frag.removeAllViews();
                    Toast.makeText(getApplicationContext(), "stationery Clicked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_printing:
                    setTitle(R.string.title_printing);
                    frag = findViewById(R.id.fragment2);
                    frag.removeAllViews();
                    Toast.makeText(getApplicationContext(), "printing Clicked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_faxAndCalls:
                    setTitle(R.string.title_faxAndCalls);
                    frag = findViewById(R.id.fragment2);
                    frag.removeAllViews();
                    Toast.makeText(getApplicationContext(), "faxAndCalls Clicked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_expenses:
                    setTitle(R.string.title_expenses);
                    frag = findViewById(R.id.fragment2);
                    frag.removeAllViews();
                    Expenses expensesFrg = new Expenses();
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    ft.add(R.id.fragment2, expensesFrg);
                    ft.commit();
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
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setTitle(R.string.title_home);

        Home homeFrg = new Home();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment2, homeFrg);
        ft.commit();
    }
}

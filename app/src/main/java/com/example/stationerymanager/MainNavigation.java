package com.example.stationerymanager;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.Toast;

public class MainNavigation extends AppCompatActivity {
    private FragmentManager fm ;
    private FragmentTransaction ft;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment Frg = null;
            ConstraintLayout frag = findViewById(R.id.fragment2);
            frag.removeAllViews();
            String title = "";
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    title = getString(R.string.title_home);
                    Frg = new Home();
                    break;
                case R.id.navigation_stationery:
                    title = getString(R.string.title_stationery);
                    break;
                case R.id.navigation_printing:
                    title = getString(R.string.title_printing);
                    break;
                case R.id.navigation_faxAndCalls:
                    title = getString(R.string.title_faxAndCalls);
                    break;
                case R.id.navigation_expenses:
                    title = getString(R.string.title_expenses);
                    Frg = new Expenses();
                    break;
            }
            setTitle(title);
            Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
            if (Frg != null){
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.add(R.id.fragment2, Frg);
                ft.commit();
            }
            return true;
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

package com.example.splashscreen.LocationOwner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.splashscreen.R;
import com.example.splashscreen.databases.SessionManager;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.HashMap;

public class RetailerDashboard extends AppCompatActivity {

    ChipNavigationBar bottomNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_dashboard);

        bottomNavigationBar = findViewById(R.id.bottom_nav_menu);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new retailerManageFragment()).commit();

        bottomMenu();
    }

    private void bottomMenu() {

        bottomNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.bottom_nav_dashboard:
                        fragment = new retailerDashboardFragment();
                        break;
                    case R.id.bottom_nav_manage:
                        fragment = new retailerManageFragment();
                        break;
                    case R.id.bottom_nav_profile:
                        fragment = new profileFragment();
                        break;
                    case R.id.bottom_nav_search:
                        fragment = new searchFragment();
                        break;
                    case R.id.bottom_nav_home:
                        fragment = new homeFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
    }
}
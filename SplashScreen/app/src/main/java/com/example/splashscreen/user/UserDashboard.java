package com.example.splashscreen.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.splashscreen.HelperClasses.HomeAdapter.CatogeryAdapter;
import com.example.splashscreen.HelperClasses.HomeAdapter.CatogeryHelperClass;
import com.example.splashscreen.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.splashscreen.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.splashscreen.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.example.splashscreen.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.example.splashscreen.R;
import com.example.splashscreen.comman.LoginSignUp;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //variable
    static final float END_SCALE = 0.7f;

    RecyclerView featuredRecycler,catogeryRecycler,mostViewedRecycler;
    RecyclerView.Adapter adapter;
    LinearLayout contentView;
    ImageView menuIcon,addPlace;

    //drawer bottom_nav_menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(com.example.splashscreen.R.layout.activity_user_dashboard);

        //hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        catogeryRecycler = findViewById(R.id.catogery_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content_view);
        addPlace = findViewById(R.id.add_place_btn);

        //bottom_nav_menu hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);


        addProfile();
        navigationDrawer();
        featuredRecycler();
        catogeryRecycler();
        mostViewedRecycler();;
    }

    private void addProfile() {
        addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginSignUp.class));
            }
        });
    }


    //navigation drawer functions start
    private void navigationDrawer() {

        //navigation drawer

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(drawerLayout.isDrawerVisible(GravityCompat.START)){

                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });


        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                //scale the view based on the current slide offset
                final float diffScaledOffset = slideOffset*(1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //translate the view, accounting for the scaled width
                final float xOffset = drawerView.getWidth()*slideOffset;
                final float xOffsetDiff = contentView.getWidth()*diffScaledOffset/2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_all_categories:
                startActivity(new Intent(getApplicationContext(),AllCatogeries.class));

                break;

        }


        return true;
    }

    //navigation drawer function end

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.mcdonald_img,"Mcdonald's","dfkjasd dkfjsfjf dfdkfjdjf fdfdfd dfjd ddfsfdf  fsdfdf"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.mcdonald_img,"Mcdonald's","dfkjasd dkfjsfjf dfdkfjdjf fdfdfd dfjd ddfsfdf  fsdfdf"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.mcdonald_img,"Mcdonald's","dfkjasd dkfjsfjf dfdkfjdjf fdfdfd dfjd ddfsfdf  fsdfdf"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.mcdonald_img,"Mcdonald's","dfkjasd dkfjsfjf dfdkfjdjf fdfdfd dfjd ddfsfdf  fsdfdf"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

    }

    private void catogeryRecycler(){

        catogeryRecycler.setHasFixedSize(true);
        catogeryRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<CatogeryHelperClass> catogeryLocations = new ArrayList<>();
        catogeryLocations.add(new CatogeryHelperClass(R.drawable.restra,"Restaurent"));
        catogeryLocations.add(new CatogeryHelperClass(R.drawable.restra,"Restaurent"));
        catogeryLocations.add(new CatogeryHelperClass(R.drawable.restra,"Restaurent"));
        catogeryLocations.add(new CatogeryHelperClass(R.drawable.restra,"Restaurent"));
        catogeryLocations.add(new CatogeryHelperClass(R.drawable.restra,"Restaurent"));

        adapter = new CatogeryAdapter(catogeryLocations);
        catogeryRecycler.setAdapter(adapter);

    }

    private void mostViewedRecycler(){

        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.mcdonald_img,"Mcdonald's","dfkjasd dkfjsfjf dfdkfjdjf fdfdfd dfjd ddfsfdf  fsdfdf"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.mcdonald_img,"Mcdonald's","dfkjasd dkfjsfjf dfdkfjdjf fdfdfd dfjd ddfsfdf  fsdfdf"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.mcdonald_img,"Mcdonald's","dfkjasd dkfjsfjf dfdkfjdjf fdfdfd dfjd ddfsfdf  fsdfdf"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.mcdonald_img,"Mcdonald's","dfkjasd dkfjsfjf dfdkfjdjf fdfdfd dfjd ddfsfdf  fsdfdf"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.mcdonald_img,"Mcdonald's","dfkjasd dkfjsfjf dfdkfjdjf fdfdfd dfjd ddfsfdf  fsdfdf"));

        adapter = new MostViewedAdapter(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);

    }



}
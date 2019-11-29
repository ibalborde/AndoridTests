package com.example.rosariorescue.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.rosariorescue.AnimalDataSource;
import com.example.rosariorescue.StaticAlbums;
import com.example.rosariorescue.R;
import com.example.rosariorescue.adapters.ViewPagerAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.drawer_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);

        //add fragments here
        AnimalDataSource catsDataSource = new AnimalDataSource("Cats", StaticAlbums.AnimalsListCats);
        AnimalDataSource dogsDataSource = new AnimalDataSource("Dogs", StaticAlbums.AnimalsListDogs);
        AnimalDataSource otherDataSource = new AnimalDataSource("Others", StaticAlbums.AnimalsListOthers);

        ViewPager viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(catsDataSource);
        viewPagerAdapter.addFragment(dogsDataSource);
        viewPagerAdapter.addFragment(otherDataSource);
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout_selector);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_cat_black_48dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog_black_48dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_hospital_box_outline_black_48dp);
        tabLayout.setupWithViewPager(viewPager);

       this.initCollapsingToolbar();

        try {
            Glide.with(this).load(R.drawable.cat1).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //adding floating button

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(view ->  {
//            Intent myIntent = new Intent(MainActivity.this, PhotoPickerActivity.class);
//            startActivity(myIntent);
//        });

        OneSignal.startInit(this)
                .setNotificationReceivedHandler(new ExampleNotificationReceivedHandler())
                .init();

    }

    class ExampleNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
        @Override
        public void notificationReceived(OSNotification notification) {
            JSONObject data = notification.payload.additionalData;
            String customKey;

            Log.i("OneSignalExample", "Recibi la noti");

            if (data != null) {
                customKey = data.optString("customkey", null);
                if (customKey != null)
                    Log.i("OneSignalExample", "customkey set with value: " + customKey);
            }
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar titleTextView on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the titleTextView when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

}
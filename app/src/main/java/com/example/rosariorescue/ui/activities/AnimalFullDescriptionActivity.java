package com.example.rosariorescue.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.rosariorescue.R;
import com.example.rosariorescue.StaticAlbums;
import com.example.rosariorescue.adapters.AnimalsPagerAdapter;
import com.example.rosariorescue.models.Animal;
import com.example.rosariorescue.ui.activities.SocialLoginActivity;

import java.util.ArrayList;
import java.util.List;


public class AnimalFullDescriptionActivity extends AppCompatActivity implements View.OnClickListener {
    final String TAG = "AnimalFullDescription";
    private String animal_status;
    private String animal_description;
    private int animal_position;
    private String animal_types;
    private List<Animal> AnimalsList, auxAnimalList;
    private Animal animal;
    private ViewPager viewPager;
    private AnimalsPagerAdapter animalPagerAdapter;
    private ImageButton button_next, button_previous;
    private LinearLayout dotsIndicator;
    private ImageView[] dots;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(saveInstanceState);
        setContentView(R.layout.animal_full_information);
        AnimalsList = new ArrayList<>();

        //toolbar
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.baseline_keyboard_arrow_left_white_36);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        button_next = findViewById(R.id.btn_next);
        button_previous = findViewById(R.id.btn_previous);
        button_next.setOnClickListener(this);
        button_previous.setOnClickListener(this);

        if (getIntent().hasExtra("animal_type") && getIntent().hasExtra("animal_position")) {
            animal_types = getIntent().getStringExtra("animal_type");
            animal_position = getIntent().getIntExtra("animal_position", 0);

            AnimalsList = getIncomingIntent(animal_types, animal_position);

            viewPager = findViewById(R.id.viewPagerFullDescription);
            animalPagerAdapter = new AnimalsPagerAdapter(this, AnimalsList.get(animal_position));
            viewPager.setAdapter(animalPagerAdapter);
        }

        //dots
        dotsIndicator = findViewById(R.id.dots_indicator);
        createDots(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        MenuItem shareIcon = menu.findItem(R.id.action_share);
        if(TAG.equals("AnimalFullDescription")){
            shareIcon.setVisible(true);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        //startActivityForResult(myIntent, 0);

        switch (item.getItemId()) {
            case R.id.action_share:
                Log.d(TAG, "type and pos: " + animal_types +" "+ animal_position );
                Intent myIntent = new Intent(this, SocialLoginActivity.class);
                myIntent.putExtra("animal_position", animal_position);
                myIntent.putExtra("animal_type", animal_types);
                startActivity(myIntent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Animal> getIncomingIntent(String animal_types, int animal_position) {

        switch (animal_types) {
            case "CAT":
                auxAnimalList = StaticAlbums.AnimalsListCats;
                break;
            case "DOG":
                auxAnimalList = StaticAlbums.AnimalsListDogs;
                break;
            case "OTHER":
                auxAnimalList = StaticAlbums.AnimalsListOthers;
                break;
            default:
                break;
        }

        animal = auxAnimalList.get(animal_position);
        animal_status = animal.getStatusString();
        animal_description = animal.getDescription();

        setInfo(animal_status, animal_description);
        return auxAnimalList;
    }

    private void setInfo(String status, String description) {
        TextView animal_Status = findViewById(R.id.status_animal_full);
        TextView animal_Description = findViewById(R.id.animal_description_full);
        animal_Status.setText(status);
        animal_Description.setText(description);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_next:
                loadNextSlide();
                break;
            case R.id.btn_previous:
                loadPreviousSlide();
                break;
        }
    }

    private void loadNextSlide(){
        int next_slide = viewPager.getCurrentItem()+1;
        Log.d("DAT", "next " + next_slide);
        if(next_slide < animal.getPhotos().size()){
            viewPager.setCurrentItem(next_slide);
        }
    }

    private void loadPreviousSlide(){
        int previous_slide = viewPager.getCurrentItem()-1;
        Log.d("DAT", "previous " + previous_slide);
        if(previous_slide >= 0){
            viewPager.setCurrentItem(previous_slide);
        }
    }

    private void createDots(int currentPosition){
        if(dotsIndicator != null){
            dotsIndicator.removeAllViews();
        }
        int length = animal.getPhotos().size();
        Log.d("DAT", "CantFotos "+ length);
        dots = new ImageView[length];
        for(int i = 0; i < length; i++){
            dots[i] = new ImageView(this);
            if(i == currentPosition){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));
            }else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);

            dotsIndicator.addView(dots[i], params);
        }
    }
}

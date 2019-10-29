package com.example.rosariorescue;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

public class AnimalFullDescription extends AppCompatActivity implements View.OnClickListener {
    private String animal_status;
    private String animal_description;
    private int animal_position;
    private String animal_types;
    private List<Animal> AnimalsList, auxAnimalList;
    private Animal animal;
    private ViewPager viewPager;
    private AdapterForAnimalsPager animalPagerAdapter;
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
            animalPagerAdapter = new AdapterForAnimalsPager(this, AnimalsList.get(animal_position));
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        //startActivityForResult(myIntent, 0);

        switch (item.getItemId()) {
            case R.id.action_share:
                        Toast.makeText(getApplicationContext(), "Share Photo!", Toast.LENGTH_LONG).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Animal> getIncomingIntent(String animal_types, int animal_position) {

        switch (animal_types) {
            case "cats":
                auxAnimalList = prepareAlbumsCats();
                break;
            case "dogs":
                auxAnimalList = prepareAlbumsDogs();
                break;
            case "others":
                auxAnimalList = prepareAlbumsOthers();
                break;
            default:
                break;
        }

        Log.d("DAT2", "Position " + getIntent().hasExtra("animal_position"));
        animal = auxAnimalList.get(animal_position);
        animal_status = animal.getStatus() == 0 ? "Buscado" : "Encontrado";
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

    //Estos albums deberia estar en base de datos(GOTO) :D

    private List<Animal> prepareAlbumsOthers() {
        List<Integer> cover1 = new ArrayList<>();
        List<Integer> cover2 = new ArrayList<>();
        List<Integer> cover3 = new ArrayList<>();
        List<Integer> cover4 = new ArrayList<>();
        List<Integer> cover5 = new ArrayList<>();
        cover1.add(R.drawable.other2);
        cover1.add(R.drawable.other3);
        cover1.add(R.drawable.other8);
        cover2.add(R.drawable.other4);
        cover3.add(R.drawable.other5);
        cover4.add(R.drawable.other6);
        cover5.add(R.drawable.other7);

        Animal a = new Animal("30-09-19", 3, cover1.get(0), "Test Description", 1, cover1);
        AnimalsList.add(a);

        a = new Animal("29-09-19", 1, cover2.get(0), "Test Description", 1, cover2);
        AnimalsList.add(a);

        a = new Animal("28-09-19", 1, cover3.get(0), "Test Description", 1, cover3);
        AnimalsList.add(a);

        a = new Animal("29-09-19", 1, cover4.get(0), "Test Description", 1, cover4);
        AnimalsList.add(a);

        a = new Animal("28-09-19", 1, cover5.get(0), "Test Description", 1, cover5);
        AnimalsList.add(a);

        return AnimalsList;
    }

    private List<Animal> prepareAlbumsCats() {
        List<Integer> cover1 = new ArrayList<>();
        List<Integer> cover2 = new ArrayList<>();
        List<Integer> cover3 = new ArrayList<>();
        cover1.add(R.drawable.cat2);
        cover1.add(R.drawable.cat3);
        cover1.add(R.drawable.cat4);
        cover2.add(R.drawable.cat3);
        cover3.add(R.drawable.cat4);


        Animal a = new Animal("Cuco", 3, cover1.get(0), "Test Description cat1", 1, cover1);
        AnimalsList.add(a);

        a = new Animal("Gato2", 1, cover2.get(0), "Test Description cat2", 1, cover2);
        AnimalsList.add(a);

        a = new Animal("Gato3", 1, cover3.get(0), "Test Description cat3", 0, cover3);
        AnimalsList.add(a);

        return AnimalsList;
    }

    private List<Animal> prepareAlbumsDogs() {
        List<Integer> cover1 = new ArrayList<>();
        List<Integer> cover2 = new ArrayList<>();
        List<Integer> cover3 = new ArrayList<>();
        List<Integer> cover4 = new ArrayList<>();
        List<Integer> cover5 = new ArrayList<>();
        cover1.add(R.drawable.dog1);
        cover1.add(R.drawable.dog2);
        cover2.add(R.drawable.dog3);
        cover3.add(R.drawable.dog4);
        cover4.add(R.drawable.dog5);
        cover5.add(R.drawable.dog6);

        Animal a = new Animal("30-09-19", 2, cover1.get(0), "Test Description", 1, cover1);
        AnimalsList.add(a);

        a = new Animal("29-09-19", 1, cover2.get(0), "Test Description", 1, cover2);
        AnimalsList.add(a);

        a = new Animal("28-09-19", 1, cover3.get(0), "Test Description", 1, cover3);
        AnimalsList.add(a);

        a = new Animal("12-09-19", 1, cover4.get(0), "Test Description", 1, cover4);
        AnimalsList.add(a);

        a = new Animal("03-09-19", 1, cover5.get(0), "Test Description", 1, cover5);
        AnimalsList.add(a);

        return AnimalsList;
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
        if(next_slide < animal.getNumOfPhtos()){
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
        int length = animal.getNumOfPhtos();
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

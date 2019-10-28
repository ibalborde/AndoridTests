package com.example.rosariorescue;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AnimalFullDescription extends AppCompatActivity {
    private int animal_image;
    private String animal_status;
    private String animal_description;
    private int animal_position;
    private String animal_types;
    private List<Animal> AnimalsList;
    private Animal animal;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.animal_full_information);
        AnimalsList = new ArrayList<>();

        getIncomingIntent();
    }

    private void getIncomingIntent(){

        if(getIntent().hasExtra("animal_type") && getIntent().hasExtra("animal_position")){
            animal_types = getIntent().getStringExtra("animal_type");
                    switch(animal_types) {
                        case "cats":
                            prepareAlbumsCats();
                            break;
                        case "dogs":
                            prepareAlbumsDogs();
                            break;
                        case "others":
                            prepareAlbumsOthers();
                            break;
                        default:
                            break;
                    }

            Log.d("DAT2","Position " + getIntent().hasExtra("animal_position"));

            animal_position = getIntent().getIntExtra("animal_position", 0);
            animal = AnimalsList.get(animal_position);
            animal_image = animal.getThumbnail();
            animal_status = animal.getStatus() == 0 ? "Buscado" : "Encontrado";
            animal_description = animal.getDescription();

            setInfo(animal_image, animal_status, animal_description);

        }
    }

    private void setInfo(int image, String status, String description){
        ImageView animal_Image = findViewById(R.id.animal_image_full);
        TextView animal_Status = findViewById(R.id.status_animal_full);
        TextView animal_Description = findViewById(R.id.animal_description_full);

        Glide.with(this).load(image).into(animal_Image);
        animal_Status.setText(status);
        animal_Description.setText(description);

    }

    //Estos albums deberia estar en base de datos(GOTO) :D

    private void prepareAlbumsOthers() {
        int[] covers = new int[]{
                R.drawable.other2,
                R.drawable.other3,
                R.drawable.other4,
                R.drawable.other5,
                R.drawable.other6,
                R.drawable.other7,
                R.drawable.other8};

        Animal a = new Animal("30-09-19", 13, covers[0], "Test Description", 1);
        AnimalsList.add(a);

        a = new Animal("29-09-19", 8, covers[1], "Test Description", 1);
        AnimalsList.add(a);

        a = new Animal("28-09-19", 11, covers[2], "Test Description", 1);
        AnimalsList.add(a);

        a = new Animal("29-09-19", 8, covers[3], "Test Description", 1);
        AnimalsList.add(a);

        a = new Animal("28-09-19", 11, covers[4], "Test Description", 1);
        AnimalsList.add(a);

        a = new Animal("29-09-19", 8, covers[5], "Test Description", 1);
        AnimalsList.add(a);

        a = new Animal("28-09-19", 11, covers[6], "Test Description", 1);
        AnimalsList.add(a);
    }

    private void prepareAlbumsCats() {
        int[] covers = new int[]{
                R.drawable.cat2,
                R.drawable.cat3,
                R.drawable.cat4};

        Animal a = new Animal("Cuco", 13, covers[0], "Test Description cat1", 1);
        AnimalsList.add(a);

        a = new Animal("Gato2", 8, covers[1], "Test Description cat2", 0);
        AnimalsList.add(a);

        a = new Animal("Gato3", 11, covers[2], "Test Description cat3", 1);
        AnimalsList.add(a);
    }

    private void prepareAlbumsDogs() {
        int[] covers = new int[]{
                R.drawable.dog1,
                R.drawable.dog2,
                R.drawable.dog3,
                R.drawable.dog4,
                R.drawable.dog5,
                R.drawable.dog6};

        Animal a = new Animal("30-09-19", 13, covers[0], "Test Description", 1);
        AnimalsList.add(a);

        a = new Animal("29-09-19", 8, covers[1], "Test Description", 1);
        AnimalsList.add(a);

        a = new Animal("28-09-19", 11, covers[2], "Test Description", 1);
        AnimalsList.add(a);

        a = new Animal("12-09-19", 13, covers[3], "Test Description", 1);
        AnimalsList.add(a);

        a = new Animal("03-09-19", 8, covers[4], "Test Description", 1);
        AnimalsList.add(a);

        a = new Animal("18-09-19", 11, covers[5], "Test Description", 1);
        AnimalsList.add(a);
    }
}

package com.example.rosariorescue;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class AnimalFullDescription extends AppCompatActivity {
    private int animal_image;
    private String animal_status;
    private String animal_description;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.animal_full_information);

        getIncomingIntent();
    }

    private void getIncomingIntent(){

        Bundle bundle=this.getIntent().getExtras();


        if(getIntent().hasExtra("animal_image") && getIntent().hasExtra("animal_status") && getIntent().hasExtra("animal_description")){
            animal_image = getIntent().getExtras().getInt("animal_image");
            animal_status = getIntent().getStringExtra("animal_status");
            animal_description = getIntent().getStringExtra("animal_description");

            setInfo(animal_image, animal_status, animal_description);

        }
    }

    private void setInfo(int image, String status, String description){
        ImageView animal_Image = findViewById(R.id.animal_image_full);
        TextView animal_Status = findViewById(R.id.status_animal_full);
        TextView animal_Description = findViewById(R.id.animal_description_full);

        Glide.with(this).load(animal_image).into(animal_Image);
        animal_Status.setText(animal_status);
        animal_Description.setText(animal_description);

    }
}

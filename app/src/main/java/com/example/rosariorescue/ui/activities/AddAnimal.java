package com.example.rosariorescue.ui.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rosariorescue.R;

public class AddAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(saveInstanceState);
        setContentView(R.layout.add_animal);
    }
}

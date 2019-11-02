package com.example.rosariorescue;

import com.example.rosariorescue.models.Animal;

import java.util.List;

public class AnimalDataSource {

    public String title;

    public List<Animal> animals;

    // MARK: - Init

    public AnimalDataSource(String title, List<Animal> animals) {
        this.title = title;
        this.animals = animals;
    }

}

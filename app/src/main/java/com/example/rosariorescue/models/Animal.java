package com.example.rosariorescue.models;

import java.util.List;

public class Animal {

    // MARK: - Data

    private String name;

    private int thumbnail;

    private String description;

    //0 buscado, 1 encontrado
    private int status;

    private List<Integer> photos;

    // MARK: - Init

    public Animal() {}

    public Animal(String name, int thumbnail, String description, int status, List<Integer> photos) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.description = description;
        this.status = status;
        this.photos = photos;
    }

    //Getter

    public String getName() {
        return name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public int getPhotosCount() {
        return photos.size();
    }

    public String getDescription(){
        return description;
    }

    public int getStatus(){
        return status;
    }

    public List<Integer> getPhotos(){
        return photos;
    }

}


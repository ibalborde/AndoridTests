package com.example.rosariorescue.models;

import java.util.Date;
import java.util.List;

public class Animal {

    // MARK: - Data

    private String fecha;

    private int thumbnail;

    private String description;

    private int status;

    private List<Integer> photos;

    private int animal_type;

    // MARK: - Init

    public Animal() {}

    public Animal(String fecha, int thumbnail, String description, int status, List<Integer> photos, int animal_type) {
        this.fecha = fecha;
        this.thumbnail = thumbnail;
        this.description = description;
        this.status = status;
        this.photos = photos;
        this.animal_type = animal_type;
    }

    //Getter

    public String getFecha() {
        return fecha;
    }

    public int getThumbnail() {
        return thumbnail;
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

    public boolean isBuscado() {
        return this.status == 0;
    }

    public boolean isEncontrado() {
        return this.status == 1;
    }

    public String getStatusString() {
        return isBuscado() ? "Buscado" : "Encontrado";
    }

    public int isCat() {
        return this.animal_type = 0;
    }

    public int isDog() {
        return this.animal_type = 1;
    }

    public int isOther() {
        return this.animal_type = 2;
    }

    public String getAnimalTypeString() {
        if(animal_type == 0) {
            return "CAT";
        }
        else if(animal_type == 1){
            return "DOG";
            }
        else {
            return "OTHER";
        }
    }

}


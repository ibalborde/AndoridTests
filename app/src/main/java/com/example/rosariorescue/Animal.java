package com.example.rosariorescue;

class Animal {
    private String name;
    private int numOfPhtos;
    private int thumbnail;
    private String description;
    private int status; //0 buscado, 1 encontrado

    public Animal() {
    }

    public Animal(String name, int numOfPhtos, int thumbnail, String description, int status) {
        this.name = name;
        this.numOfPhtos = numOfPhtos;
        this.thumbnail = thumbnail;
        this.description = description;
        this.status = status;

    }

    //Getter

    public String getName() {
        return name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public int getNumOfPhtos() {
        return numOfPhtos;
    }

    public String getDescription(){
        return description;
    }

    public int getStatus(){
        return status;
    }

    //Setter

    public void setNumOfPhtos(int numOfPhtos) {
        this.numOfPhtos = numOfPhtos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setDescription (String description){
        this.description = description;
    }

    public void setStatus (int status){
        this.status = status;
    }
}


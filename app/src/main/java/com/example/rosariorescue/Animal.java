package com.example.rosariorescue;

class Animal {
    private String name;
    private int numOfSongs;
    private int thumbnail;
    private String description;

    public Animal() {
    }

    public Animal(String name, int numOfSongs, int thumbnail, String description) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
        this.description = description;
    }

    //Getter

    public String getName() {
        return name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public String getDescription(){
        return description;
    }

    //Setter

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
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
}


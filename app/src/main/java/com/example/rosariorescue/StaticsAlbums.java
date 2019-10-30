package com.example.rosariorescue;

import java.util.ArrayList;
import java.util.List;

class StaticAlbums {

    static final List<Integer> coverOthers1 = new ArrayList<Integer>() {{
        add(R.drawable.other2);
        add(R.drawable.other3);
        add(R.drawable.other8);
    }};
    static final List<Integer> coverOthers2 = new ArrayList<Integer>() {{
        add(R.drawable.other4);
    }};
    static final List<Integer> coverOthers3 = new ArrayList<Integer>() {{
        add(R.drawable.other5);
    }};
    static final List<Integer> coverOthers4 = new ArrayList<Integer>() {{
        add(R.drawable.other6);
        }};
    static final List<Integer> coverOthers5 = new ArrayList<Integer>() {{
        add(R.drawable.other7);
    }};

    static final Animal other1 = new Animal("30-09-19", 3, coverOthers1.get(0), "Test Description", 1, coverOthers1);
    static final Animal other2 = new Animal("29-09-19", 1, coverOthers2.get(0), "Test Description", 1, coverOthers2);
    static final Animal other3 = new Animal("28-09-19", 1, coverOthers3.get(0), "Test Description", 1, coverOthers3);
    static final Animal other4 = new Animal("29-09-19", 1, coverOthers4.get(0), "Test Description", 1, coverOthers4);
    static final Animal other5 = new Animal("28-09-19", 1, coverOthers5.get(0), "Test Description", 1, coverOthers5);
    static final List<Animal> AnimalsListOthers = new ArrayList<Animal>() {{
        add(other1);
        add(other2);
        add(other3);
        add(other4);
        add(other5);
    }};




    static final List<Integer> coverCats1 = new ArrayList<Integer>() {{
        add(R.drawable.cat2);
        add(R.drawable.cat3);
        add(R.drawable.cat4);
    }};
    static final List<Integer> coverCats2 = new ArrayList<Integer>() {{
        add(R.drawable.cat3);
    }};
    static final List<Integer> coverCats3 = new ArrayList<Integer>() {{
        add(R.drawable.cat4);
    }};

    static final Animal cat1 = new Animal("Cuco", 3, coverCats1.get(0), "Test Description cat1", 1, coverCats1);
    static final Animal cat2 = new Animal("Gato2", 1, coverCats2.get(0), "Test Description cat2", 1, coverCats2);
    static final Animal cat3 = new Animal("Gato3", 1, coverCats3.get(0), "Test Description cat3", 0, coverCats3);

    static final List<Animal> AnimalsListCats = new ArrayList<Animal>() {{
        add(cat1);
        add(cat2);
        add(cat3);
    }};




    static final List<Integer> coverDogs1 = new ArrayList<Integer>() {{
        add(R.drawable.dog1);
    }};
    static final List<Integer> coverDogs2 = new ArrayList<Integer>() {{
        add(R.drawable.dog2);
    }};
    static final List<Integer> coverDogs3 = new ArrayList<Integer>() {{
        add(R.drawable.dog3);
    }};
    static final List<Integer> coverDogs4 = new ArrayList<Integer>() {{
        add(R.drawable.dog4);
    }};
    static final List<Integer> coverDogs5 = new ArrayList<Integer>() {{
        add(R.drawable.dog5);
    }};
    static final List<Integer> coverDogs6 = new ArrayList<Integer>() {{
        add(R.drawable.dog6);
    }};

    static final Animal dog1 = new Animal("30-09-19", 1, coverDogs1.get(0), "Test Description", 1, coverDogs1);
    static final Animal dog2 = new Animal("29-09-19", 1, coverDogs2.get(0), "Test Description", 1, coverDogs2);
    static final Animal dog3 = new Animal("28-09-19", 1, coverDogs3.get(0), "Test Description", 1, coverDogs3);
    static final Animal dog4 = new Animal("12-09-19", 1, coverDogs4.get(0), "Test Description", 1, coverDogs4);
    static final Animal dog5 = new Animal("03-09-19", 1, coverDogs5.get(0), "Test Description", 1, coverDogs5);
    static final Animal dog6 = new Animal("03-09-19", 1, coverDogs5.get(0), "Test Description", 1, coverDogs5);

    static final List<Animal> AnimalsListDogs = new ArrayList<Animal>() {{
        add(dog1);
        add(dog2);
        add(dog3);
        add(dog4);
        add(dog5);
        add(dog6);
    }};

}

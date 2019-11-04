package com.example.rosariorescue;

import android.text.format.DateUtils;

import com.example.rosariorescue.models.Animal;

import java.util.ArrayList;
import java.util.List;

public class StaticAlbums {

    public static final String DATE_FORMAT = "dd-MMM-yyyy";
    //puclic static final String mDateTime = DateUtils.getDateFromTimeStamp(System.currentTimeMillis(),DATE_FORMAT)

    private static final List<Integer> coverOthers1 = new ArrayList<Integer>() {{
        add(R.drawable.other2);
        add(R.drawable.other3);
        add(R.drawable.other8);
    }};
    private static final List<Integer> coverOthers2 = new ArrayList<Integer>() {{
        add(R.drawable.other4);
    }};
    private static final List<Integer> coverOthers3 = new ArrayList<Integer>() {{
        add(R.drawable.other5);
    }};
    private static final List<Integer> coverOthers4 = new ArrayList<Integer>() {{
        add(R.drawable.other6);
        }};
    private static final List<Integer> coverOthers5 = new ArrayList<Integer>() {{
        add(R.drawable.other7);
    }};

    private static final Animal other1 = new Animal("11-10-19", coverOthers1.get(0), "Test Description", 1, coverOthers1,2 );
    private static final Animal other2 = new Animal("29-09-19", coverOthers2.get(0), "Test Description", 1, coverOthers2,2);
    private static final Animal other3 = new Animal("28-09-19", coverOthers3.get(0), "Test Description", 1, coverOthers3,2);
    private static final Animal other4 = new Animal("29-09-19", coverOthers4.get(0), "Test Description", 1, coverOthers4,2);
    private static final Animal other5 = new Animal("28-09-19", coverOthers5.get(0), "Test Description", 1, coverOthers5,2);
    public static final List<Animal> AnimalsListOthers = new ArrayList<Animal>() {{
        add(other1);
        add(other2);
        add(other3);
        add(other4);
        add(other5);
    }};




    private static final List<Integer> coverCats1 = new ArrayList<Integer>() {{
        add(R.drawable.cat2);
        add(R.drawable.cat3);
        add(R.drawable.cat4);
    }};
    private static final List<Integer> coverCats2 = new ArrayList<Integer>() {{
        add(R.drawable.cat3);
    }};
    private static final List<Integer> coverCats3 = new ArrayList<Integer>() {{
        add(R.drawable.cat4);
    }};

    private static final Animal cat1 = new Animal("Cuco", coverCats1.get(0), "Test Description cat1", 1, coverCats1,0);
    private static final Animal cat2 = new Animal("23-09-19", coverCats2.get(0), "Test Description cat2", 1, coverCats2,0);
    private static final Animal cat3 = new Animal("11-10-19", coverCats3.get(0), "Test Description cat3", 0, coverCats3,0);

    public static final List<Animal> AnimalsListCats = new ArrayList<Animal>() {{
        add(cat1);
        add(cat2);
        add(cat3);
    }};

    private static final List<Integer> coverDogs1 = new ArrayList<Integer>() {{
        add(R.drawable.dog1);
    }};

    private static final List<Integer> coverDogs2 = new ArrayList<Integer>() {{
        add(R.drawable.dog2);
    }};

    private static final List<Integer> coverDogs3 = new ArrayList<Integer>() {{
        add(R.drawable.dog3);
    }};

    private static final List<Integer> coverDogs4 = new ArrayList<Integer>() {{
        add(R.drawable.dog4);
    }};

    private static final List<Integer> coverDogs5 = new ArrayList<Integer>() {{
        add(R.drawable.dog5);
    }};

    private static final List<Integer> coverDogs6 = new ArrayList<Integer>() {{
        add(R.drawable.dog6);
    }};

    private static final Animal dog1 = new Animal("30-09-19", coverDogs1.get(0), "Test Description", 1, coverDogs1,1);
    private static final Animal dog2 = new Animal("29-09-19", coverDogs2.get(0), "Test Description", 1, coverDogs2,1);
    private static final Animal dog3 = new Animal("28-09-19", coverDogs3.get(0), "Test Description", 1, coverDogs3,1);
    private static final Animal dog4 = new Animal("12-09-19", coverDogs4.get(0), "Test Description", 1, coverDogs4,1);
    private static final Animal dog5 = new Animal("03-09-19", coverDogs5.get(0), "Test Description", 1, coverDogs5,1);
    private static final Animal dog6 = new Animal("03-09-19", coverDogs5.get(0), "Test Description", 1, coverDogs5,1);

    public static final List<Animal> AnimalsListDogs = new ArrayList<Animal>() {{
        add(dog1);
        add(dog2);
        add(dog3);
        add(dog4);
        add(dog5);
        add(dog6);
    }};

}

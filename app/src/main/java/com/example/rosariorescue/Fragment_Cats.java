package com.example.rosariorescue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_Cats extends Fragment {

    View v;
    private RecyclerView myRecyclerView;
    private List<Animal> listAnimal;
    private AnimalAdapter animalAdapter;

    public Fragment_Cats() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_cats, container, false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_cats);
        animalAdapter = new AnimalAdapter(getContext(), listAnimal);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(animalAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listAnimal = new ArrayList<>();

        prepareAlbums();
    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.cat2,
                R.drawable.cat3,
                R.drawable.cat4};

        Animal a = new Animal("30-09-19", 13, covers[0]);
        listAnimal.add(a);

        a = new Animal("29-09-19", 8, covers[1]);
        listAnimal.add(a);

        a = new Animal("28-09-19", 11, covers[2]);
        listAnimal.add(a);


    }
}
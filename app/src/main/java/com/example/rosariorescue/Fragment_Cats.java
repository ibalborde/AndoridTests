package com.example.rosariorescue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_Cats extends Fragment implements AnimalAdapter.OnAnimalCardListener {

    View v;
    private RecyclerView myRecyclerView;
    private List<Animal> AnimalsList;
    private AnimalAdapter animalAdapter;

    public Fragment_Cats() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_cats, container, false);
        View b = inflater.inflate(R.layout.dialog_animal_pager, container, false);
        myRecyclerView =  v.findViewById(R.id.recycler_view_cats);
        animalAdapter = new AnimalAdapter(getContext(), AnimalsList, this);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(animalAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AnimalsList = new ArrayList<>();

        prepareAlbums();
    }

    private void prepareAlbums() {
        List<Integer> cover1 = new ArrayList<>();
        List<Integer> cover2 = new ArrayList<>();
        List<Integer> cover3 = new ArrayList<>();
        cover1.add(R.drawable.cat2);
        cover1.add(R.drawable.cat3);
        cover1.add(R.drawable.cat4);
        cover2.add(R.drawable.cat3);
        cover3.add(R.drawable.cat4);


        Animal a = new Animal("Cuco", 3, cover1.get(0), "Test Description cat1", 1, cover1);
        AnimalsList.add(a);

        a = new Animal("Gato2", 1, cover2.get(0), "Test Description cat2", 1, cover2);
        AnimalsList.add(a);

        a = new Animal("Gato3", 1, cover3.get(0), "Test Description cat3", 0, cover3);
        AnimalsList.add(a);
    }


    @Override
    public void onAnimalCardClick(int position) {

        Log.d("DAT","Position " + position);
        Intent intent = new Intent(getContext(), AnimalFullDescription.class);
        intent.putExtra("animal_position", position);
        intent.putExtra("animal_type", "cats");
        getContext().startActivity(intent);
    }
}

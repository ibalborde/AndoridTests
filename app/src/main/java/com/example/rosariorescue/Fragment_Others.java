package com.example.rosariorescue;

import android.content.Intent;
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

public class Fragment_Others extends Fragment implements AnimalAdapter.OnAnimalCardListener {

    View v;

    private RecyclerView myRecyclerView;
    private List<Animal> AnimalsList;
    private AnimalAdapter animalAdapter;

    public Fragment_Others() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_others, container, false);
        myRecyclerView = v.findViewById(R.id.recycler_view_others);
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
        List<Integer> cover4 = new ArrayList<>();
        List<Integer> cover5 = new ArrayList<>();
        cover1.add(R.drawable.other2);
        cover1.add(R.drawable.other3);
        cover1.add(R.drawable.other8);
        cover2.add(R.drawable.other4);
        cover3.add(R.drawable.other5);
        cover4.add(R.drawable.other6);
        cover5.add(R.drawable.other7);

        Animal a = new Animal("30-09-19", 3, cover1.get(0), "Test Description", 1, cover1);
        AnimalsList.add(a);

        a = new Animal("29-09-19", 1, cover2.get(0), "Test Description", 1, cover2);
        AnimalsList.add(a);

        a = new Animal("28-09-19", 1, cover3.get(0), "Test Description", 1, cover3);
        AnimalsList.add(a);

        a = new Animal("29-09-19", 1, cover4.get(0), "Test Description", 1, cover4);
        AnimalsList.add(a);

        a = new Animal("28-09-19", 1, cover5.get(0), "Test Description", 1, cover5);
        AnimalsList.add(a);
    }

    @Override
    public void onAnimalCardClick(int position) {
        Intent intent = new Intent(getContext(), AnimalFullDescription.class);
        intent.putExtra("animal_position", position);
        intent.putExtra("animal_type", "others");
        getContext().startActivity(intent);
    }
}

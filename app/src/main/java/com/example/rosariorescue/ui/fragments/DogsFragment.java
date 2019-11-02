package com.example.rosariorescue.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosariorescue.ui.activities.AnimalFullDescriptionActivity;
import com.example.rosariorescue.R;
import com.example.rosariorescue.StaticAlbums;
import com.example.rosariorescue.adapters.AnimalAdapter;
import com.example.rosariorescue.models.Animal;

public class DogsFragment extends Fragment implements AnimalAdapter.OnAnimalCardListener {

    View v;
    private RecyclerView myRecyclerView;

    private List<Animal> AnimalsList;
    private AnimalAdapter animalAdapter;

    public DogsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_dogs, container, false);

        myRecyclerView = v.findViewById(R.id.recycler_view_dogs);
        animalAdapter = new AnimalAdapter(getContext(), AnimalsList, this);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(animalAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AnimalsList = StaticAlbums.AnimalsListDogs;

    }


    @Override
    public void onAnimalCardClick(int position) {
        Intent intent = new Intent(getContext(), AnimalFullDescriptionActivity.class);
        intent.putExtra("animal_position", position);
        intent.putExtra("animal_type", "dogs");
        getContext().startActivity(intent);
    }
}

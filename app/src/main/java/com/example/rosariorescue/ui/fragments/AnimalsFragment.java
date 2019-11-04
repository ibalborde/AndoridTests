package com.example.rosariorescue.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosariorescue.AnimalDataSource;
import com.example.rosariorescue.R;
import com.example.rosariorescue.adapters.AnimalAdapter;
import com.example.rosariorescue.models.Animal;
import com.example.rosariorescue.ui.activities.AnimalFullDescriptionActivity;

import java.util.List;

public class AnimalsFragment extends Fragment implements AnimalAdapter.OnAnimalCardListener {

    // MARK: - Data

    private List<Animal> animals;
    private String animal_type;

    // MARK: - Init

    public AnimalsFragment(AnimalDataSource dataSource) {
        this.animals = dataSource.animals;
    }

    // MARK: - Life Cycle

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = container.getContext();

        View view = inflater.inflate(R.layout.fragment_cats, container, false);

        AnimalAdapter animalAdapter = new AnimalAdapter(context, animals, this);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_cats);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(animalAdapter);

        animal_type = view.getTransitionName();

        return view;
    }

    @Override
    public void onAnimalCardClick(int position) {
        Intent intent = new Intent(getContext(), AnimalFullDescriptionActivity.class);
        intent.putExtra("animal_position", position);
        intent.putExtra("animal_type", animals.get(position).getAnimalTypeString());
        getContext().startActivity(intent);
    }
}
package com.example.rosariorescue;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    private Animal animal;
    private Dialog mDialog;
    private TextView descriptionDialog;
    private ImageView imageDialog;


    public Fragment_Others() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_others, container, false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_others);
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
        int[] covers = new int[]{
                R.drawable.other2,
                R.drawable.other3,
                R.drawable.other4,
                R.drawable.other5,
                R.drawable.other6,
                R.drawable.other7,
                R.drawable.other8};

        Animal a = new Animal("30-09-19", 13, covers[0], "Test Description");
        AnimalsList.add(a);

        a = new Animal("29-09-19", 8, covers[1], "Test Description");
        AnimalsList.add(a);

        a = new Animal("28-09-19", 11, covers[2], "Test Description");
        AnimalsList.add(a);

        a = new Animal("29-09-19", 8, covers[3], "Test Description");
        AnimalsList.add(a);

        a = new Animal("28-09-19", 11, covers[4], "Test Description");
        AnimalsList.add(a);

        a = new Animal("29-09-19", 8, covers[5], "Test Description");
        AnimalsList.add(a);

        a = new Animal("28-09-19", 11, covers[6], "Test Description");
        AnimalsList.add(a);


    }

    @Override
    public void onAnimalCardClick(int position) {
        mDialog = new Dialog(getContext());
        mDialog.setContentView(R.layout.dialog_animal);

        animal = AnimalsList.get(position);

        descriptionDialog = mDialog.findViewById(R.id.dialog_description_id);
        imageDialog = mDialog.findViewById(R.id.dialog_image_id);

        descriptionDialog.setText(animal.getDescription());
        imageDialog.setImageResource(animal.getThumbnail());
        mDialog.show();

    }
}
